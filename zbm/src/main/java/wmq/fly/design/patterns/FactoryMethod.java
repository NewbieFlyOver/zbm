package wmq.fly.design.patterns;
/**
 * 工厂方法模式:创建型模式
 * 好处就是：如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。
 * 这样做，拓展性较好！符合开闭原则
 *
 */

interface Sender{
	public void send();
}
//两个实现类
class MailSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is mailsender!");  
	}
}

class SmsSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is sms sender!");  
	}
}

//工厂类接口
interface Provider {  
    public Sender produce();  
}  
//两个工厂实现类
class SendMailFactory implements Provider {  
    
    @Override  
    public Sender produce(){  
        return new MailSender();  
    }  
}  

class SendSmsFactory implements Provider{  
	  
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }  
}  

public class FactoryMethod {

	public static void main(String[] args) {
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.send();
	}
}
