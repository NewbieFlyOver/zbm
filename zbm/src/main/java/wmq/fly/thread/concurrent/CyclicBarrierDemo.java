package wmq.fly.thread.concurrent;

import java.util.concurrent.CyclicBarrier;

class ThreadDemo extends Thread{
	CyclicBarrier cyclicBarrier;
	public ThreadDemo(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}
	@Override
	public void run() {
		System.out.println("开始处理数据喽喽喽..........");
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("处理数据结束..........");
		try {
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("唤醒并并行执行..........");
	}
}
/**
 * CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。
 * 当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。 
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		for(int i=0; i<5; i++) {
			new ThreadDemo(cyclicBarrier).start();
		}

	}
}
