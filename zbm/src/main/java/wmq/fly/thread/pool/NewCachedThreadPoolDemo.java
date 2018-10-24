package wmq.fly.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *
 */
public class NewCachedThreadPoolDemo {
	
	public static void main(String[] args) {
		// 无限大小线程池 jvm自动回收
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			 final int temp = i;
			newCachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);

				}
			});
		}
	}
}
