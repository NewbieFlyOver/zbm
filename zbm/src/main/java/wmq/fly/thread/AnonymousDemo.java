package wmq.fly.thread;

public class AnonymousDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			//run方法中编写多线程需要执行的代码
			@Override
			public void run() {
				for(int i=0; i<5; i++) {
					System.out.println("childern:"+i);
				}
			}
		});
		//启动线程
		thread.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("main:"+i);
		}
	}
}
