package wmq.fly.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TCP服务器端
class TcpService{
	public static void main(String[] args) throws IOException {
		System.out.println("TCP协议服务器端启动....");
		//创建服务器端连接
		ServerSocket serverSocket = new ServerSocket(8089);
		//接收客户端请求 阻塞功能
		Socket accept = serverSocket.accept();
		InputStream inputStream = accept.getInputStream();
		//将字节流转换成string类型
		byte[] bytes = new byte[1024];
		int read = inputStream.read(bytes);
	    String result = new String(bytes,0,read);
	    System.out.println("服务器端接收客户端内容："+result);
	    serverSocket.close();
	}
}

//TCP服务器端之使用多线程
class TcpServiceThread{
	public static void main(String[] args) throws IOException {
		System.out.println("TCP协议服务器端启动....");
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		//创建服务器端连接
		ServerSocket serverSocket = new ServerSocket(8089);
		try {
			while(true) {
				//接收客户端请求 阻塞功能
				final Socket accept = serverSocket.accept();
				newCachedThreadPool.execute(new Runnable() {
					
					@Override
					public void run() {
						InputStream inputStream;
						try {
							inputStream = accept.getInputStream();
							//将字节流转换成string类型
							byte[] bytes = new byte[1024];
							int read = inputStream.read(bytes);
						    String result = new String(bytes,0,read);
						    System.out.println("服务器端接收客户端内容："+result);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			serverSocket.close();
		}
	}
}

//TCP客户端
class TcpClient{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("socket tcp客户端启动....");
		Socket socket = new Socket("127.0.0.1",8089);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("我是秦凡".getBytes());
		socket.close();
	}
}


public class TcpDemo {

}
