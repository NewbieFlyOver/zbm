package wmq.fly.thread.communication;
/**
 * 线程间通信之二：使用lock锁只是解决了线程安全问题，并没有解决消费者重复消费问题
 * 使用Condition中的await()与sigin()实现消费者重复消费问题，即生产者线程消费一个，消费者线程立马消费；
 * 生产者没有任何生产，消费者不能读；消费者，没有消费完，生产者不能在继续生产。
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源源实体类
 *
 */
class PeopleLock{
	String name;
	String sex;
	boolean flag = false;
	//在共享资源里定义lock锁
	Lock lock = new ReentrantLock();
}

/**
 * 写线程类
 *
 */
class OutputLockThread extends Thread{
	PeopleLock people;
	Condition newCondition;
	
	public OutputLockThread(PeopleLock people,Condition newCondition) {
		this.people = people;
		this.newCondition = newCondition;
	}
	
	@Override
	public void run() {
		int count = 0 ;
		while(true) {
			try {
				//开启锁
				people.lock.lock();
				
				if(people.flag) {
					try {
						// 当前线程变为等待（从运行状态变为等待状态），但是可以释放锁
						newCondition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(count==0) {
					people.name = "方向";
					people.sex = "女";
				}else {
					people.name = "目标";
					people.sex = "男";
				}
				count = (count+1)%2;
				people.flag= true;
				//唤醒其他等待线程（从等待状态变为运行状态）
				newCondition.signal();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//关闭锁
				people.lock.unlock();
			}
		}
	}
}

/**
 * 读线程类
 *
 */
class InputLockThread extends Thread{
	PeopleLock people;
	Condition newCondition;
	
	public InputLockThread(PeopleLock people,Condition newCondition) {
		this.people = people;
		this.newCondition = newCondition;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				//开启锁
				people.lock.lock();
				if(!people.flag) {
					try {
						// 当前线程变为等待（从运行状态变为等待状态），但是可以释放锁
						newCondition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(people.name+",  "+people.sex);
				people.flag= false;
				//唤醒其他等待线程（从等待状态变为运行状态）
				newCondition.signal();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//关闭锁
				people.lock.unlock();
			}
		}
	}
}

public class LockThread {
	public static void main(String[] args) {
		//共享people对象
		PeopleLock people = new PeopleLock();
		//同一个newCondition
		Condition newCondition = people.lock.newCondition();
		
		OutputLockThread out = new OutputLockThread(people,newCondition);
		InputLockThread input = new InputLockThread(people,newCondition);
		
		out.start();
		input.start();
	}
}
