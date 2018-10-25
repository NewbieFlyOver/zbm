package wmq.fly.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 * 非公平锁，CAS， 非可重入锁
 * benfit:响应速度更快， 因为不切换线程状态
 * bad:线程数量达到一定量时， 性能下降
 */
/*
	当一个线程 调用这个不可重入的自旋锁去加锁的时候没问题，因为自旋锁的持有引用为空；当再次调用lock()的时候，因为自旋锁的持有引用已经不为空了，该线程对象会误认为是别人的线程持有了自旋锁
	使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null，并且预测值为当前线程。
	当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入临界区。
*/
class SpinLock implements Lock{
    private AtomicReference<Thread> sign = new AtomicReference<Thread>();

    public void lock(){
        Thread current=Thread.currentThread();
        //compareAndSet(expect：预期值,update：更新值)使用了CSA无锁机制
        // 如果当前状态值等于预期值，则以原子方式将同步状态设置为给定的更新值
        while(!sign.compareAndSet(null, current)){
        	
        }
    }

    public void unlock(){
        Thread current=Thread.currentThread();
        //如果当前值等于预期值current，则将更新值赋值给当前值
        sign.compareAndSet(current, null);
    }

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}



public class SpinlocksDemo implements Runnable{
	static int sum;
	private SpinLock lock;

	public SpinlocksDemo(SpinLock lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		this.lock.lock();
		this.lock.lock();
		sum++;
		this.lock.unlock();
		this.lock.unlock();
	}
	
	public static void main(String[] args) throws InterruptedException {
		SpinLock lock = new SpinLock();
		for (int i = 0; i < 100; i++) {
			SpinlocksDemo test = new SpinlocksDemo(lock);
			Thread t = new Thread(test);
			t.start();
		}

		Thread.currentThread().sleep(1000);
		System.out.println(sum);
	}
}
