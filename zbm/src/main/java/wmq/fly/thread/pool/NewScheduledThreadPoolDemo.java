package wmq.fly.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。 
 *
 */
public class NewScheduledThreadPoolDemo {
	
	public static void main(String[] args) {
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
		for (int i = 0; i < 10; i++) {
			 final int temp = i;
			 newScheduledThreadPool.schedule(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ",i:" + temp);
				}
			},3,TimeUnit.SECONDS);
		}
	}
}
