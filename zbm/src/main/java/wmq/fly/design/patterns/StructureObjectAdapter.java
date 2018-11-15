package wmq.fly.design.patterns;

/**
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 * 
 * 
 * 对象的适配器模式:  结构模式
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
 *
 */

class SourceObject{
	public void method() {
		System.out.println("this is origin method");
	}
}

interface TargetTable{
	public void method();
	public void newMehtod();
}

class Wrapper implements TargetTable{
	
	private SourceObject sourceObject;
	
	public Wrapper(SourceObject sourceObject) {
		this.sourceObject = sourceObject;
	}

	@Override
	public void method() {
		sourceObject.method();
	}

	@Override
	public void newMehtod() {
		System.out.println("this is newMehtod");
	}	
}

public class StructureObjectAdapter {
	public static void main(String[] args) {
		TargetTable t = new Wrapper(new SourceObject());
		t.method();
		t.newMehtod();
	}
}
