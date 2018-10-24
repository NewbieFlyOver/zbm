package wmq.fly.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 型号量的使用
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(new Runnable() {
			public void run() {
				System.out.println("1.子线程..............");
				countDownLatch.countDown();//每次减去1
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println("2.子线程..............");
				countDownLatch.countDown();//每次减去1
			}
		}).start();
		
		countDownLatch.await();// 调用当前方法主线程阻塞  countDown结果为0, 阻塞变为运行状态
		System.out.println("主线程..............");
	}
}
