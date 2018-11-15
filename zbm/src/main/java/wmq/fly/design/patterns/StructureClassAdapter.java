package wmq.fly.design.patterns;

/**
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 * 
 * 
 * 类的适配器模式:  结构模式
 * 核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口是Targetable，通过Adapter类，将Source的功能扩展到Targetable里
 *
 */

class Source{
	public void method() {
		System.out.println("this is origin method");
	}
}

interface TargetTabel{
	//与原类中的方法相同
	public void method();
	
	//新类的方法
	public void newMethod();
}

//Adapter类继承Source类，实现Targetable接口
class Adapter extends Source implements TargetTabel{

	@Override
	public void newMethod() {
		System.out.println("this is targetMethos");
	}
}

public class StructureClassAdapter {
	public static void main(String[] args) {
		TargetTabel targetTabel = new Adapter();
		targetTabel.method();
		targetTabel.newMethod();
	}
}
