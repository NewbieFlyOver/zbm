package wmq.fly.design.patterns;

/**
 *  桥接模式：  结构模式
 * 
 *      桥接模式就是把事物和其具体实现分开，使他们可以各自独立的变化。桥接的用意是：将抽象化与实现化解耦，
 * 使得二者可以独立变化，像我们常用的JDBC桥DriverManager一样，JDBC进行连接数据库的时候，在各个数据库之间进行切换，
 * 基本不需要动太多的代码，甚至丝毫不用动，原因就是JDBC提供统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接就行了。
 * 
 *  通过对Bridge类的调用，实现了对接口Sourceable的实现类SourceSub1和SourceSub2的调用。
 */


interface SourceableB{
	public void method();
}

//定义两个实现类
class SourceSub1B implements SourceableB{

	@Override
	public void method() {
		System.out.println("this is SourceSub1B");
	}
}

class SourceSub2B implements SourceableB{

	@Override
	public void method() {
		System.out.println("this is SourceSub2B");
	}
}

//定义一个桥，持有sourceableB的一个实例：
abstract class Bridge{
	private SourceableB sourceableB;

	public void method() {
		this.sourceableB.method();
	}
	
	public SourceableB getSourceableB() {
		return sourceableB;
	}

	public void setSourceableB(SourceableB sourceableB) {
		this.sourceableB = sourceableB;
	}
}


class MyBridge extends Bridge{
	public void method() {
		getSourceableB().method();
	}
}

public class StructureBridge {
	public static void main(String[] args) {
		
		Bridge b = new MyBridge();
		
		SourceableB sb1 = new SourceSub1B();
		b.setSourceableB(sb1);
		b.method();
		
		SourceableB sb2 = new SourceSub2B();
		b.setSourceableB(sb2);
		b.method();
		
	}
	
}
