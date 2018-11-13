package wmq.fly.design.patterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *  原型模式：创建型模式
 *  	虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
 *  浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
 * 	深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
 *
 *  注：深复制中涉及到的对象要实现Serializable进行序列化
 *  
 *  序列化：对象的寿命通常随着生成该对象的程序的终止而终止，有时候需要把在内存中的各种对象的状态（也就是实例变量，不是方法,静态变量属于类的状态，因此 序列化并不保存静态变量）
 *  		保存下来，并且可以在需要时再将对象恢复。虽然你可以用你自己的各种各样的方法来保存对象的状态，但是Java给你提供一种应该比你自己的好的保存对象状态的机制，那就是序列化。
 *          Java 序列化技术可以使你将一个对象的状态写入一个Byte 流里（系列化），并且可以从其它地方把该Byte 流里的数据读出来（反序列化）。
 *  序列化的用途：
 *  	1. 想把的内存中的对象状态保存到一个文件中或者数据库中时候
 *		2. 想把对象通过网络进行传播的时候
 */

class Business implements Serializable{
	//使用transient关键字修饰的的变量，在序列化对象的过程中，该属性不会被序列化。在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。
	
	//序列化id
	private static final long serialVersionUID = 1L;
	private String addr;
	
	public Business(String addr) {
		this.addr = addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr() {
		return addr;
	}
}

class CloneDemo implements Cloneable,Serializable {
	private String name;
	private Integer age;
	Business business = new Business("上海");
	
	public CloneDemo(String name,Integer age) {
		this.name = name;
		this.age = age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	//浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
	protected Object shallowClone() throws CloneNotSupportedException {
		CloneDemo business = (CloneDemo) super.clone();
		return business;
	}

	//深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。  
    public Object deepClone() throws IOException, ClassNotFoundException {  
  
        // 写入当前对象的二进制流  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
        bos.close();
        oos.close();
  
        // 读出二进制流产生的新对象  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis); 
        bis.close();
        ois.close();
        //反序列化，调用父类中的无参构函数。
        return ois.readObject();  
    } 
	
	
	@Override
	public String toString() {
		return "ShallowClone [name=" + name + ", age=" + age + ", business=" + business.getAddr() + "]";
	}

}

public class CreateTypePrototype {
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		
		CloneDemo prototype = new CloneDemo("铁军",20);
		//调用深复制
		CloneDemo prototypeClone = (CloneDemo) prototype.deepClone();
		prototype.setAge(18);
		prototype.business.setAddr("南京");
		System.out.println(prototypeClone.toString());
		System.out.println(prototype.toString());
		
		
	}
}
