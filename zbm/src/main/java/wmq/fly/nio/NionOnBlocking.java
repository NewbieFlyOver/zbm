package wmq.fly.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;



/**
 * NIO非阻塞代码
 *
 */

//客户端
class NioCliet{
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("客户端已经启动。。。。。");
		//1.创建通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8090));
		//2.切换异步非阻塞
		sChannel.configureBlocking(false);
		//3.指定缓冲区大小
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String str = scanner.next();
			byteBuffer.put((new Date().toString()+"\n"+str).getBytes());
			//4.切换为读取模式
			byteBuffer.flip();
			sChannel.write(byteBuffer);
			byteBuffer.clear();
		}
	}
}

//服务器端
class NioServer{
	/*
	在SelectionKey类的源码中我们可以看到如下的4中属性：
	1、SelectionKey.OP_CONNECT：可连接
	2、SelectionKey.OP_ACCEPT：可接受连接
	3、SelectionKey.OP_READ：可读
	4、SelectionKey.OP_WRITE：可写
	如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来，如下：
	int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;*/
	
	public static void main(String[] args) throws IOException {
		System.out.println("服务器端已经启动.......");
		//1.创建通道
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		//2.切换读取模式: 切换异步非阻塞
		sChannel.configureBlocking(false);
		//3.绑定连接
		sChannel.bind(new InetSocketAddress(8090));
		//4.获取选择器
		Selector selector = Selector.open();
		//5.将通道注册到选择器，并且指定监听接收事件
		sChannel.register(selector, SelectionKey.OP_ACCEPT);
		//6.轮训式 获取选择 已经准备就绪 的事件
		while(selector.select()>0) {
			//7.获取当前选择器所有注册的 选择键（已经就绪的监听事件）
			Iterator<SelectionKey> it= selector.selectedKeys().iterator();
			while(it.hasNext()) {
				//8.获取准备就绪的事件
				SelectionKey sk = it.next();
				//9.判断具体是什么事件准备就绪
				if(sk.isAcceptable()) {
					//10.若接收就绪，获取客户端连接
					SocketChannel socketChannel = sChannel.accept();
					//11.设置阻塞模式
					socketChannel.configureBlocking(false);
					//12.将该通道注册到服务器上
					socketChannel.register(selector, SelectionKey.OP_READ);
				}else if(sk.isReadable()) {
					//13.获取当前选择器 就绪 状态的通道
					SocketChannel socketChannel = (SocketChannel)sk.channel();
					//14.读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while((len = socketChannel.read(buf))>0) {
						buf.flip();
						System.out.println(new String(buf.array(),0,len));
						buf.clear();
					}
				}
				it.remove();
			}
		}
		while(true) {
			System.out.println("---------------------------------------");
		}
	}
}



public class NionOnBlocking {

}
