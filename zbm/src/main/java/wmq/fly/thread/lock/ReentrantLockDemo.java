package wmq.fly.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock重入锁
 *
 */
class ReentrantLockLock extends Thread {
	ReentrantLock lock = new ReentrantLock();
	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getId()+"  get");
		set();
		lock.unlock();
	}
	public void set() {
		lock.lock();
		System.out.println(Thread.currentThread().getId()+"  set");
		lock.unlock();
	}
	@Override
	public void run() {
		get();
	}
}

/**
 * Synchronized重入锁
 *
 */
class SynchronizedLock implements Runnable {
	public  synchronized void get() {
		System.out.println("name:" + Thread.currentThread().getName() + " get();");
		set();
	}

	public synchronized  void set() {
		System.out.println("name:" + Thread.currentThread().getName() + " set();");
	}

	@Override

	public void run() {
		get();
	}
}
/**
 * 重进入是指任意线程在获取到锁之后，再次获取该锁而不会被该锁所阻塞。
 *
 */
public class ReentrantLockDemo {
	public static void main(String[] args) {
		ReentrantLockLock ss = new ReentrantLockLock();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
		
		SynchronizedLock sl = new SynchronizedLock();
		new Thread(sl).start();
		new Thread(sl).start();
		new Thread(sl).start();
	}
}

