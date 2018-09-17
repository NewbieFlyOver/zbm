package wmq.fly.eventListener;

import org.springframework.context.ApplicationEvent;

/**
 *  自定义监听方法
 *
 */
public class SecondEvent  extends ApplicationEvent {
	
	private String str;
	
	private boolean flag;

	/*
	 *  在自定义事件的构造方法中除了第一个source参数，其他参数都可以去自定义
	 */
	public SecondEvent(Object source, String str) {
		super(source);
		this.str = str;
	}
	
	/*
	 *  自定义监听器触发的方法
	 */
	public void printStr (String str) {
		System.out.println("自定义监听方法触发："+str);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = true;
	}
	
	

}
