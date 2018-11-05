package wmq.fly.proxy;
/**
 *   静态代理
 *
 */

//代理对象
class Car{
	
	public void say() {
		System.out.println("汽车行驶中........");
	}
}

//代理类
class ProxyDemo{
	
	private Car car;
	public ProxyDemo(Car car) {
		this.car = car;
	}
	
	public void say() {
		System.out.println("启动喽~~~~~");
		car.say();
		System.out.println("停车哈**********");
	}
}

public class StaticProxyDemo {

	public static void main(String[] args) {
		ProxyDemo proxy = new ProxyDemo(new Car());
		proxy.say();
	}
}

