package wmq.fly.thread.communication;
/**
 * 线程间通信，加上synchronized只是解决了线程安全问题，并没有解决消费者重复消费问题
 *
 * synchronized的缺点：效率低、扩展性不好、不能自动化
 */


/**
 * 共享资源源实体类
 *
 */
class People{
	String name;
	String sex;
}

/**
 * 写线程类
 *
 */
class OutputThread extends Thread{
	People people;
	public OutputThread(People people) {
		this.people = people;
	}
	
	@Override
	public void run() {
		int count = 0 ;
		while(true) {
			//解决线程安全问题加上synchronized
			synchronized(people) {
				if(count==0) {
					people.name = "方向";
					people.sex = "女";
				}else {
					people.name = "目标";
					people.sex = "男";
				}
			}
			count = (count+1)%2;
		}
	}
}

/**
 * 读线程类
 *
 */
class InputThread extends Thread{
	People people;
	public InputThread(People people) {
		this.people = people;
	}
	
	@Override
	public void run() {
		while(true) {
			//解决线程安全问题加上synchronized
			synchronized(people) {
				System.out.println(people.name+",  "+people.sex);
			}
		}
	}
}

public class OutInputThread {
	public static void main(String[] args) {
		//共享people对象
		People people = new People();
		
		OutputThread out = new OutputThread(people);
		InputThread input = new InputThread(people);
		
		out.start();
		input.start();
	}
}
