package wmq.fly.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 1. 直接缓冲区与非直接缓冲区
 * 2. 分散读取聚集写入
 * 3. 字符集 Charset
 */
public class DirectBuffer {
	
	//直接缓冲区
	@Test
	public void directBuffer() throws IOException {
		long starTime = System.currentTimeMillis();
		//创建管道
		FileChannel inChannel = FileChannel.open(Paths.get("fc1.png"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("fc2.png"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		//定义映射文件
		MappedByteBuffer inMapBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMapBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
	
		//直接对缓冲区操作
		byte[] dsf = new byte[inMapBuffer.limit()];
		inMapBuffer.get(dsf);
		outMapBuffer.put(dsf);
		inChannel.close();
		outChannel.close();
		long endTime = System.currentTimeMillis();
	    System.out.println("操作直接缓冲区耗时时间："+(endTime-starTime));
	}
	
	//非直接缓冲区
	@Test
	public void notDirectBuffer() throws IOException {
		long starTime = System.currentTimeMillis();
		//读入流
		FileInputStream fis = new FileInputStream("fc1.png");
		//写入流
		FileOutputStream fos = new FileOutputStream("fc3.png");
		//创建通道
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		//分配并指定缓冲区大小
		ByteBuffer allocate = ByteBuffer.allocate(1024);
		while(inChannel.read(allocate) !=-1) {
			//开启读取模式
			allocate.flip();
			//将数据写入到通道中
			outChannel.write(allocate);
			allocate.clear();
		}
		//关闭通道、关闭连接
		fis.close();
		fos.close();
		inChannel.close();
		outChannel.close();
		long endTime = System.currentTimeMillis();
	    System.out.println("操作非直接缓冲区耗时时间："+(endTime-starTime));
	}

	//分散读取聚集写入
	@Test
	public void ScatteredReadsClusteredWrites() throws IOException {
		//随机访问
		RandomAccessFile raf = new RandomAccessFile("fc1.png", "rw");
		//获取通道
		FileChannel channel = raf.getChannel();
		//分配指定大小指定缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		//分散读取
		ByteBuffer[] bufs = {buf1,buf2};
		channel.read(bufs);
		
		for(ByteBuffer bb:bufs) {
			//切换成读模式
			bb.flip();
		}
		//聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("fc4.png", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(bufs);
		raf.close();
		raf2.close();	
	}
	
	//字符集 Charset
	@Test
	public void charset() throws CharacterCodingException {
		// 获取编码器
		Charset cs1 = Charset.forName("GBK");
		// 获取加密器
		CharsetEncoder ce = cs1.newEncoder();
		// 获取解码器
		CharsetDecoder cd = cs1.newDecoder();
		CharBuffer cBuf = CharBuffer.allocate(1024);
		cBuf.put("不抛弃不放弃");
		cBuf.flip();
		// 编码加密
		ByteBuffer bBuf = ce.encode(cBuf);
		for (int i = 0; i < 12; i++) {
			System.out.println(bBuf.get());
		}
		// 编码解密
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.toString());
		System.out.println("-------------------------------------");
		Charset cs2 = Charset.forName("utf-8");
		bBuf.flip();
		CharBuffer cbeef = cs2.decode(bBuf);
		System.out.println(cbeef.toString());
	}
	
	
}
