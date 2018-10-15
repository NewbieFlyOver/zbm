package wmq.fly.thread.stop;

class StopThread extends Thread{
	//使用volatile关键字保证原子可见性
	private volatile boolean flag = true;
	
	@Override
	public void run() {
		System.out.println("子线程运行begin。。。。。");
		while(flag) {
			
		}
		System.out.println("子线程运行end。。。。。");
	}
	
	public void stopThread() {
		this.flag = false;
	}
}


public class StopThreadDemo {
	public static void main(String[] args) {
		StopThread stopThread = new StopThread();
		stopThread.start();
		
		for(int i=0; i<5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			if(i==3) {
				stopThread.stopThread();
			}
		}
	}
}