package wmq.fly.thread.safety;

import java.util.concurrent.atomic.AtomicInteger;
/**
 *  使用java.util.concurrent.atomic并发包中的AtomicInteger类实现线程原子性与可见性
 *
 */
public class VolatileNoAtomic extends Thread {
	
	//private static volatile int count;

	private static AtomicInteger count = new AtomicInteger(0);
	private static void addCount() {
		for (int i = 0; i < 1000; i++) {
			//count++;
			//count加1
			 count.incrementAndGet();
		}
		System.out.println(count);
	}

	public void run() {
		addCount();
	}

	public static void main(String[] args) {

		VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}

		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
	}
}