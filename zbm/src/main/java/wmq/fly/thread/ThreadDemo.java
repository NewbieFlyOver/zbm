package wmq.fly.thread;

public class ThreadDemo{

	public static void main(String[] args) {
		//调用线程
		Thread01 thread01 = new Thread01();
		//设置该线程为守护线程,与主线程一起销毁
		thread01.setDaemon(true);
		thread01.start();
		for(int i=0; i<5; i++) {
			System.out.println("main:"+i);
		}
		System.out.println("主线程执行完毕~~~~~");
	}
}


class Thread01 extends Thread{
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("childern:"+i);
		}
	}
}