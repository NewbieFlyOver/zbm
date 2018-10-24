package wmq.fly.thread.concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;
/**
 * 它是一个基于链接节点的无界线程安全队列。该队列的元素遵循先进先出的原则。
 * 头是最先加入的，尾是最近加入的，该队列不允许null元素。
 *
 */
public class ConcurrentLinkedDequeDemo {

	public static void main(String[] args) {
		ConcurrentLinkedDeque cd = new ConcurrentLinkedDeque();
		cd.offer("hello");
		cd.offer("world");
		cd.offer("!");
		
		//从头获取元素,删除该元素
		System.out.println(cd.poll());
		//从头获取元素,不刪除该元素
		System.out.println(cd.peek());
		//获取总长度
		System.out.println(cd.size());
		
	}
}
