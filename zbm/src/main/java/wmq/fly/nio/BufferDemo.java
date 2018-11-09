package wmq.fly.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.junit.Test;


/**
 * ByteBuffer非直接缓冲区的用法
 *
 */

public class BufferDemo {
	
	@Test
	public void bufferDemo1(){
		try {
			ByteBuffer allocate = ByteBuffer.allocate(1024);
			System.out.println("缓冲区最大容量： "+allocate.capacity());
			System.out.println("缓冲区可以操作的数据大小： "+allocate.limit());
			System.out.println("缓冲区下一个操作的位置： "+allocate.position()); //下标从0开始
			
			System.out.println("----------------向缓冲区存放数据----------------------------");
			allocate.put("12345".getBytes());
			System.out.println("缓冲区最大容量： "+allocate.capacity());
			System.out.println("缓冲区可以操作的数据大小： "+allocate.limit());
			System.out.println("缓冲区下一个操作的位置： "+allocate.position());
			
			System.out.println("----------------开启读模式----------------------------");
			allocate.flip(); //从下标0开始读取
			System.out.println("缓冲区最大容量： "+allocate.capacity());
			System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); //此时输出为5
			System.out.println("缓冲区下一个操作的位置： "+allocate.position()); //此时输出为0
			byte[] bytes = new byte[allocate.limit()];
			allocate.get(bytes);
			System.out.println(new String(bytes, 0, bytes.length));
			
			System.out.println("----------------重复读模式----------------------------");
			allocate.rewind(); //从上次读取的地方开始读取
			System.out.println("缓冲区最大容量： "+allocate.capacity());
			System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); //此时输出为5
			System.out.println("缓冲区下一个操作的位置： "+allocate.position());
			byte[] bytes2 = new byte[allocate.limit()];
			allocate.get(bytes2);
			System.out.println(new String(bytes2, 0, bytes2.length));
			
			// clean 清空缓冲区  数据依然存在,只不过数据被遗忘
			System.out.println("----------清空缓冲区...----------");
			allocate.clear();
			System.out.println("缓冲区最大容量： "+allocate.capacity());
			System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); 
			System.out.println("缓冲区下一个操作的位置： "+allocate.position());
			byte[] bytes3 = new byte[allocate.limit()];
			allocate.get(bytes3);
			System.out.println(new String(bytes3, 0, bytes3.length));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void bufferDemo2() {
		
		ByteBuffer allocate = ByteBuffer.allocate(1024);
		allocate.put("abcd".getBytes());
		allocate.flip();
		byte[] bytes = new byte[allocate.limit()];
		allocate.get(bytes,0,2); //开启读模式
		System.out.println("输出数据："+new String(bytes,0,2));
		System.out.println("缓冲区最大容量： "+allocate.capacity());
		System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); 
		System.out.println("缓冲区下一个操作的位置： "+allocate.position());
		
		System.out.println("----------打印标记----------");
		allocate.mark(); //打印标记
		allocate.get(bytes,2,2);
		System.out.println("输出数据："+new String(bytes,2,2));
		System.out.println("缓冲区最大容量： "+allocate.capacity());
		System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); 
		System.out.println("缓冲区下一个操作的位置： "+allocate.position());
		
		System.out.println("----------还原到mark位置----------");
		allocate.reset(); //还原到mark位置
		System.out.println("缓冲区最大容量： "+allocate.capacity());
		System.out.println("缓冲区可以操作的数据大小： "+allocate.limit()); 
		System.out.println("缓冲区下一个操作的位置： "+allocate.position());
	}
	
	public static void main(String[] args) {
		
	}
	

}
