package wmq.fly.thread.safety;
/**
 * volatile关键字保证原子性
 *
 */
class threadVolatileDemo extends Thread {
	public volatile boolean flag = true;
	@Override
	public void run() {
		System.out.println("开始执行子线程....");
		while (flag) {
		}
		System.out.println("线程停止");
	}
	public void setRuning(boolean flag) {
		this.flag = flag;
	}
}
public class ThreadVolatile  {
	public static void main(String[] args) throws InterruptedException {
		threadVolatileDemo threadVolatileDemo = new threadVolatileDemo();
		threadVolatileDemo.start();
		Thread.sleep(3000);
		threadVolatileDemo.setRuning(false);
		System.out.println("flag 已经设置成false");
		Thread.sleep(1000);
		System.out.println(threadVolatileDemo.flag);
	}
}
