package wmq.fly.design.patterns;

/**
 * 代理模式：结构模式
 *  其实每个模式名称就表明了该模式的作用，代理模式就是多一个代理类出来，替原对象进行一些操作，比如我们在租房子的时候回去找中介。
 *  
 * 代理模式的应用场景：
	如果已有的方法在使用的时候需要对原有的方法进行改进，此时有两种办法：
	1、修改原有的方法来适应。这样违反了“对扩展开放，对修改关闭”的原则。
	2、就是采用一个代理类调用原有的方法，且对产生的结果进行控制。这种方法就是代理模式。
	使用代理模式，可以将功能划分的更加清晰，有助于后期维护！
 * 
 *  此处案例为静态代理
 */



interface SourceP{
	public void method();
}

class SourceTarget implements SourceP{

	@Override
	public void method() {
		System.out.println("this is SourceTarget");
	}
}

//SourceTarget的代理类
class Poxy implements SourceP{
	
	private SourceTarget sourceTarget;
	
	public Poxy() {
		//此处是与装饰模式的区别
		this.sourceTarget = new SourceTarget();
	}

	@Override
	public void method() {
		before();
		sourceTarget.method();
		atfer();
	}
	
	 private void atfer() {  
	        System.out.println("after proxy!");  
	    }  
	    private void before() {  
	        System.out.println("before proxy!");  
	    }  
}

public class StructureProxy {
	public static void main(String[] args) {
		SourceP sourceP = new Poxy();
		sourceP.method();
	}
	
}
