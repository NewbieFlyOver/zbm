package wmq.fly.thread.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  使用BlockingQueue模拟生产者与消费者
 *
 */
public class BlockingQueueDemo {
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(4);
		ProducerThread producerThread1 = new ProducerThread(queue);
		ConsumerThread consumerThread1 = new ConsumerThread(queue);
        Thread t1 = new Thread(producerThread1);
        Thread c1 = new Thread(consumerThread1);
        t1.start();
        c1.start();
        // 执行10s
        Thread.sleep(10 * 500);
        producerThread1.stop();
	}
}


class ProducerThread implements Runnable{
	BlockingQueue blockingQueue;
	private volatile boolean flag = true;
	private static AtomicInteger count = new AtomicInteger();
	
	public ProducerThread(BlockingQueue blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("生产线程启动...");
			while (flag) {
				System.out.println("正在生产数据....");
				String data = count.incrementAndGet()+"";
				// 将数据存入队列中,阻塞时间为2秒钟
				boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
				if (offer) {
					System.out.println("生产者,存入" + data + "到队列中,成功.");
				} else {
					System.out.println("生产者,存入" + data + "到队列中,失败.");
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {

		} finally {
			System.out.println("生产者退出线程");
		}
	}

	public void stop() {
		this.flag = false;
	}
}


class ConsumerThread implements Runnable {
	private BlockingQueue<String> queue;
	private volatile boolean flag = true;

	public ConsumerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("消费线程启动...");
		try {
			while (flag) {
				System.out.println("消费者,正在从队列中获取数据..");
				//poll方法：从队列中区数据并删除，阻塞时间为2秒
				String data = queue.poll(2, TimeUnit.SECONDS);
				if (data != null) {
					System.out.println("消费者,拿到队列中的数据data:" + data);
					Thread.sleep(1000);
				} else {
					System.out.println("消费者,超过2秒未获取到数据..");
					flag = false;
				}
			}
		} catch (Exception e) {
               e.printStackTrace();
		} finally {
			System.out.println("消费者退出线程...");
		}
	}
}


