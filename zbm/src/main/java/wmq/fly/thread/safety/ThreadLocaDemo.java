package wmq.fly.thread.safety;

/**
 *  创建三个线程，使用ThreadLocal让每个线程生成自己独立序列号。
 *  ThreadLocal提高一个线程的局部变量，访问某个线程拥有自己局部变量。
 *
 */

class Res {
	// 生成序列号共享变量
	public static Integer count = 0;
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {

			return 0;
		};
	};

	public Integer getNum() {
		int count = threadLocal.get() + 1;
		threadLocal.set(count);
		return count;
	}
}

public class ThreadLocaDemo extends Thread {
	private Res res;

	public ThreadLocaDemo(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res.getNum());
		}
	}

	public static void main(String[] args) {
		Res res = new Res();
		ThreadLocaDemo threadLocaDemo1 = new ThreadLocaDemo(res);
		ThreadLocaDemo threadLocaDemo2 = new ThreadLocaDemo(res);
		ThreadLocaDemo threadLocaDemo3 = new ThreadLocaDemo(res);
		threadLocaDemo1.start();
		threadLocaDemo2.start();
		threadLocaDemo3.start();
	}

}