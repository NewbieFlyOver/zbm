package wmq.fly.thread;

public class RunnableDemo {
	public static void main(String[] args) {
		//调用线程
		Thread02 t = new Thread02();
		Thread thread = new Thread(t);
		//启动线程
		thread.start();
		for(int i=0; i<5; i++) {
			System.out.println("main:"+i+"; 线程id: "+Thread.currentThread().getId()+" ;线程name: "+Thread.currentThread().getName());
		}
	}

}

class Thread02 implements Runnable{
	
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("childern:"+i+"; 线程id: "+Thread.currentThread().getId()+" ;线程name: "+Thread.currentThread().getName());
		}
	}
}