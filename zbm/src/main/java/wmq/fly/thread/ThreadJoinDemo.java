package wmq.fly.thread;

public class ThreadJoinDemo {
	//thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
	//比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
	public static void main(String[] args) {

		final Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					System.out.println("t1: "+ i);
				}
				
			}
		});
		// 注意设置了优先级， 不代表每次都一定会被执行。 只是CPU调度会有限分配
		t1.setPriority(10);
		t1.start();
		
		final Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					t1.join();// 让其他线程(t2)变为等待，直到当前t1线程执行完毕，才释放。
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0; i<10; i++) {
					System.out.println("t2: "+ i);
				}
				
			}
		});
		t2.start();
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0; i<10; i++) {
					System.out.println("t3: "+ i);
				}
				
			}
		});
		t3.start();
	}

}
