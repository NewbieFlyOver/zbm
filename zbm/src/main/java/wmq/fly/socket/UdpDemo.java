package wmq.fly.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//udp服务器端
class UdpServer{
	
	public static void main(String[] args) throws IOException {
		System.out.println("udp服务器已经启动......");
		//ip+端口号，创建服务器端口号，默认使用本机IP地址
		DatagramSocket ds = new DatagramSocket(8080);
		//服务器接收客户端1024个字节
		byte[] bytes = new byte[1024];
		//定义数据包
		DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
		//接收客户端请求，将数据封装给数据包，如果客户端不往服务器发送请求，就一直阻塞。
		ds.receive(dp);
		System.out.println("来源IP地址："+dp.getAddress()+", 端口号"+dp.getPort());
		String result = new String(dp.getData(),0,dp.getLength());
		System.out.println(result);
		ds.close();
	}
}

//udp客户端
class UdpClient{
	
	public static void main(String[] args) throws IOException {
		System.out.println("udp客户端启动连接...");
		//不传入端口号作为客户端，创建一个socket客户端
		DatagramSocket ds = new DatagramSocket();
		String str = "袁朗";
		byte[] strByte = str.getBytes();
		//定义数据包
		DatagramPacket dp = new DatagramPacket(strByte, strByte.length, InetAddress.getByName("127.0.0.1"), 8080);
		ds.send(dp);
		ds.close();
		
	}
}

public class UdpDemo {

}
