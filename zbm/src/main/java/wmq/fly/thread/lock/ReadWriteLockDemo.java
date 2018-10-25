package wmq.fly.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 但是如果有一个线程想去写这些共享资源，就不应该再有其它线程对该资源进行读或写，也就是说：读-读能共存，读-写不能共存，写-写不能共存。 
 *
 */
public class ReadWriteLockDemo {
	
	static Map<String, Object> map = new HashMap<String, Object>();
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	static Lock r = rwl.readLock();
	static Lock w = rwl.writeLock();

	// 读， 获取一个key对应的value
	public static final Object get(String key) {
		r.lock();
		try {
			System.out.println("正在做读的操作,key:" + key + " 开始");
			Thread.sleep(100);
			Object object = map.get(key);
			System.out.println("正在做读的操作,key:" + key + " 结束");
			System.out.println();
			return object;
		} catch (InterruptedException e) {

		} finally {
			r.unlock();
		}
		return key;
	}

	// 写， 设置key对应的value，并返回旧有的value
	public static final Object put(String key, Object value) {
		w.lock();
		try {

			System.out.println("正在做写的操作,key:" + key + ",value:" + value + "开始.");
			Thread.sleep(100);
			Object object = map.put(key, value);
			System.out.println("正在做写的操作,key:" + key + ",value:" + value + "结束.");
			System.out.println();
			return object;
		} catch (InterruptedException e) {

		} finally {
			w.unlock();
		}
		return value;
	}

	// 清空所有的内容
	public static final void clear() {
		w.lock();
		try {
			map.clear();
		} finally {
			w.unlock();
		}
	}

	public static void main(String[] args) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					ReadWriteLockDemo.put(i + "", i + "");
				}

			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					ReadWriteLockDemo.get(i + "");
				}

			}
		}).start();
	}

}
