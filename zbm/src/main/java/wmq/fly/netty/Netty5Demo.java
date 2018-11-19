package wmq.fly.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.internal.logging.InternalLoggerFactory;
/**
 * netty5.0 简单案例,项目中建议使用4.X版本
 *
 */
public class Netty5Demo {
}

class ServerHandler5 extends ChannelHandlerAdapter {
	// 当通道被调用,执行该方法
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 接收数据
		String value = (String) msg;
		System.out.println("Server msg:" + value);
		// 回复给客户端 “您好!”
		String res = "好的...";
		ctx.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));
	}

}

class NettyServer5 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("服务器端已经启动....");
		// 1.创建2个线程,一个负责接收客户端连接， 一个负责进行 传输数据
		NioEventLoopGroup pGroup = new NioEventLoopGroup();
		NioEventLoopGroup cGroup = new NioEventLoopGroup();
		// 2. 创建服务器辅助类
		ServerBootstrap b = new ServerBootstrap();
		b.group(pGroup, cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
				// 3.设置缓冲区与发送区大小
				.option(ChannelOption.SO_SNDBUF, 32 * 1024)
				.option(ChannelOption.SO_RCVBUF, 32 * 1024)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						//消息定长，报文大小固定长度，不够空格补全，发送和接收方遵循相同的约定，这样即使粘包了通过接收方编程实现获取定长报文也能区分。
						//sc.pipeline().addLast(new FixedLengthFrameDecoder(10));
						
						//包尾添加特殊分隔符，例如每条报文结束都添加回车换行符（例如FTP协议）或者指定特殊字符作为报文分隔符，接收方通过特殊分隔符切分报文区分。
						ByteBuf buf = Unpooled.copiedBuffer("_sucess".getBytes());
						sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
						
						//设置为String类型
						sc.pipeline().addLast(new StringDecoder());
						sc.pipeline().addLast(new ServerHandler5());
					}
				});
		ChannelFuture cf = b.bind(8090).sync();
		cf.channel().closeFuture().sync();
		pGroup.shutdownGracefully();
		cGroup.shutdownGracefully();
	}
}


class ClientHandler5 extends ChannelHandlerAdapter {
	// 当通道被调用,执行该方法
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 接收数据
		String value = (String) msg;
		System.out.println("client msg:" + value);
		super.channelRead(ctx, msg);
	}
}


class NettyClient5{
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("客户端已经启动.......");
		//创建负责接收客户端连接
		NioEventLoopGroup pGroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(pGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				//消息定长，报文大小固定长度，不够空格补全，发送和接收方遵循相同的约定，这样即使粘包了通过接收方编程实现获取定长报文也能区分。
				//ch.pipeline().addLast(new FixedLengthFrameDecoder(10));
				
				//包尾添加特殊分隔符，例如每条报文结束都添加回车换行符（例如FTP协议）或者指定特殊字符作为报文分隔符，接收方通过特殊分隔符切分报文区分。
				ByteBuf buf = Unpooled.copiedBuffer("_sucess".getBytes());
				ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new ClientHandler5());
			}
		});
		ChannelFuture cf = b.connect("127.0.0.1",8090).sync();
		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("ganxiaoning_sucess".getBytes()));
		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("ganxiaoning".getBytes()));
		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("甘小宁_sucess".getBytes()));
		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("甘小宁".getBytes()));
		// 等待客户端端口号关闭
		cf.channel().closeFuture().sync();
		pGroup.shutdownGracefully();
	}
}



