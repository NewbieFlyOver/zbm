package wmq.fly.netty;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.el.parser.AstLiteralExpression;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;


/**
 * netty 简单案例
 *
 */
public class NettyDemo {
}

class ServerHandler extends SimpleChannelHandler{
	/**
	 * 通道关闭的时候触发
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
	}

	/**
	 * 必须是连接已经建立,关闭通道的时候才会触发.
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelDisconnected(ctx, e);
		System.out.println("channelDisconnected");
	}

	/**
	 * 捕获异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("exceptionCaught");

	}

	/**
	 * 接收消息
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		super.messageReceived(ctx, e);
		System.out.println("服务器端收到客户端消息:"+e.getMessage());
		//回复内容
		ctx.getChannel().write("好的");
	}

}

//netty 服务器端
class NettyServer{
	
	public static void main(String[] args) {
		//1.创建服务类对象
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		//2.创建两个线程池 分别为监听 监听端口 、 nio监听
		ExecutorService boos = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		//3.设置工程并把两个线程池加入
		serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, worker));
		//4.设置管道工厂
		serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				/*ChannelPipeline是ChannelHandler的容器，它负责ChannelHandler的管理和事件拦截与调度。
				 * Netty的ChannelPipeline和ChannelHandler机制类似于Servlet 和Filter 过滤器，这类拦截器实际上是职责链模式的一种变形，
				 * 主要是为了方便事件的拦截和用户业务逻辑的定制。

				Netty的channel运用机制和Filter过滤器机制一样，它将Channel 的数据管道抽象为ChannelPipeline. 消息在ChannelPipeline中流动和传递。
				ChannelPipeline 持有I/O事件拦截器ChannelHandler 的链表，由ChannelHandler 对I/0 事件进行拦截和处理，
				可以方便地通过新增和删除ChannelHandler 来实现小同的业务逻辑定制，不需要对已有的ChannelHandler进行修改，能够实现对修改封闭和对扩展的支持。
				 */
				
				//5.将数据转换为String类型
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encode",new StringEncoder());
				pipeline.addLast("serverHandler", new ServerHandler());
				
				return pipeline;
			}
		});
		
		//6.绑定端口号
		serverBootstrap.bind(new InetSocketAddress(9999));
		System.out.println("netty server启动.......");
		
		while(true) {
			try {
			Thread.sleep(1000);
			System.out.println("异步非阻塞.......");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


class ClientHandler extends SimpleChannelHandler {

	
	/**
	 * 通道关闭的时候触发
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
	}

	/**
	 * 必须是连接已经建立,关闭通道的时候才会触发.
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelDisconnected(ctx, e);
		System.out.println("channelDisconnected");
	}

	/**
	 * 捕获异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("exceptionCaught");

	}

	/**
	 * 接受消息
	 */
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		super.messageReceived(ctx, e);
		System.out.println("服务器端向客户端回复内容:"+e.getMessage());
	}

}

//netty客户端
class NettyClient{
	
	public static void main(String[] args) {
		System.out.println("netty client 启动.....");
		//创建客户端
		ClientBootstrap clientBootstrap = new ClientBootstrap();
		ExecutorService boos = Executors.newCachedThreadPool();
		ExecutorService work = Executors.newCachedThreadPool();
		
		clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos, work));
		 
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				
				pipeline.addLast("clientHandler", new ClientHandler());
				
				return pipeline;
			}
		});
		ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 9999));
		Channel channel = connect.getChannel();
		System.out.println("client start");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("输入内容：");
			channel.write(scanner.next());
		}
	}
}






