package wmq.fly.design.patterns;

/**
 *  解释器模式:  关系模式 --》 通过中间类
 *		一般主要应用在OOP开发中的编译器的开发中，所以适用面比较窄。
 *	Context类是一个上下文环境类，Plus和Minus分别是用来计算的实现，基本就这样，解释器模式用来做各种各样的解释器，如正则表达式等的解释器等等！
 */


interface Expression{
	public int interpret(ContextI context);
}

class PlusI implements Expression{

	@Override
	public int interpret(ContextI context) {
		 return context.getNum1()+context.getNum2(); 
	}
	
}

class MinusI implements Expression {  
	  
    @Override  
    public int interpret(ContextI context) {  
        return context.getNum1()-context.getNum2();  
    }  
}  


class ContextI {
	
	private int num1;
	
	private int num2;

	public ContextI(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
}

public class RelationalInterpreter {

	public static void main(String[] args) {
		 // 计算9+2-8的值  
        int result = new MinusI().interpret((new ContextI(new PlusI()  
                .interpret(new ContextI(9, 2)), 8)));  
        System.out.println(result);

	}
}



