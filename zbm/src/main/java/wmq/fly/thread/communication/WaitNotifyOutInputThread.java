package wmq.fly.thread.communication;
/**
 * 线程间通信之一：加上synchronized只是解决了线程安全问题，并没有解决消费者重复消费问题
 * 使用wait()、notify()实现消费者重复消费问题，即生产者线程消费一个，消费者线程立马消费；
 * 生产者没有任何生产，消费者不能读；消费者，没有消费完，生产者不能在继续生产。
 * 注意：wait()、notify()及notifyAll()是用在synchronized代码块内的
 */

/**
 * 共享资源源实体类
 *
 */
class PeopleWaitNotify{
	String name;
	String sex;
	//
	Boolean flag = false;
}

/**
 * 写线程类
 *
 */
class OutputWaitNotifyThread extends Thread{
	PeopleWaitNotify people;
	public OutputWaitNotifyThread(PeopleWaitNotify people) {
		this.people = people;
	}
	
	@Override
	public void run() {
		int count = 0 ;
		while(true) {
			//解决线程安全问题加上synchronized
			synchronized(people) {
				if(people.flag) {
					try {
						// 当前线程变为等待（从运行状态变为等待状态），但是可以释放锁
						people.wait();
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
				//唤醒其他等待线程（从等待状态变为运行状态）
				people.notify();
				people.flag= true;
			}
			count = (count+1)%2;
			
		}
	}
}

/**
 * 读线程类
 *
 */
class InputWaitNotifyThread extends Thread{
	PeopleWaitNotify people;
	public InputWaitNotifyThread(PeopleWaitNotify people) {
		this.people = people;
	}
	
	@Override
	public void run() {
		while(true) {
			//解决线程安全问题加上synchronized
			synchronized(people) {
				if(!people.flag) {
					try {
						people.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(people.name+",  "+people.sex);
				people.notify();
				people.flag = false;
			}
		}
	}
}

public class WaitNotifyOutInputThread {
	public static void main(String[] args) {
		//共享people对象
		PeopleWaitNotify people = new PeopleWaitNotify();
		
		OutputWaitNotifyThread out = new OutputWaitNotifyThread(people);
		InputWaitNotifyThread input = new InputWaitNotifyThread(people);
		
		out.start();
		input.start();
	}
}
