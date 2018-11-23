package wmq.fly.design.patterns;

import java.util.Enumeration;
import java.util.Vector;

/**
 *  命令模式:  关系模式 --》 类之间的关系
 *	   命令模式很好理解，举个例子，司令员下令让士兵去干件事情，从整个事情的角度来考虑，司令员的作用是，发出口令，口令经过传递，传到了士兵耳朵里，士兵去执行。
 *  这个过程好在，三者相互解耦，任何一方都不用去依赖其他人，只需要做好自己的事儿就行，司令员要的是结果，不会去关注到底士兵是怎么实现的。
 * 
 *    Invoker是调用者（司令员），Receiver是被调用者（士兵），MyCommand是命令，实现了Command接口，持有接收对象，
 * 命令模式的目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开
 */

interface Command{
	public void exe();
}

class MyCommand implements Command {
 
	private Receiver receiver;
	
	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}
	
	@Override
	public void exe() {
		receiver.action();
	}
}


class Receiver {
	
 public void action(){  
        System.out.println("command received!");  
    } 
}

class Invoker{
	
	private Command command;
	
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void action() {
		command.exe();
	}
}


public class RelationalCommand {

	public static void main(String[] args) {
	
		Receiver receiver = new Receiver();
		Command mc = new MyCommand(receiver);
		Invoker ik = new Invoker(mc);
		ik.action();
	}
}



