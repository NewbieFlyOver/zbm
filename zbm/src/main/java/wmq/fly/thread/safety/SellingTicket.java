package wmq.fly.thread.safety;

public class SellingTicket {
	
	public static void main(String[] args) throws InterruptedException {
		TicketThread ticketThread = new TicketThread();
		Thread t1 = new Thread(ticketThread,"售票窗口①");
		Thread t2 = new Thread(ticketThread,"售票窗口②");
		t1.start();
		ticketThread.flag = false;
		Thread.sleep(50);
		t2.start();
	}
}

class TicketThread implements Runnable{
	private static int ticketNum = 100;
	//Object ob ；//一定要让ob初始化，不然为null值synchronized(ob)锁不起作用
	//多个线程相同步必须使用同一把锁，此处为：ob
	private Object ob = new Object();
	public boolean flag = true;
	
	@Override
	public void run() {
		while(ticketNum > 0) {
			if(flag) {
				synchronized(TicketThread.class) {  //如果有多个线程同时要执行这个代码块，只有抢到锁的那一个线程才能执行，其他没抢到锁的线程一直排队，等待其他线程释放锁。
					if(ticketNum > 0) {
						try {
							Thread.sleep(50);
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println(Thread.currentThread().getName()+"出售第："+(100-ticketNum+1)+"张票");
						ticketNum--;
					}
				}
			}else {
				sale();
			}
		}
	}
	
	public static synchronized void sale() {  //同步函数使用的是this锁
		if(ticketNum > 0) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(Thread.currentThread().getName()+"出售第："+(100-ticketNum+1)+"张票");
			ticketNum--;
		}
	}
	
		
		
	
}