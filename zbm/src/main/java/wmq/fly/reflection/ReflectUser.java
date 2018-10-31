package wmq.fly.reflection;

public class ReflectUser {
	
	private String name;
	
	private String addr;

	public ReflectUser() {
		System.out.println("调用无参函数了，哈哈哈。。。。");
	}
	
	public ReflectUser(String name, String addr) {
		super();
		System.out.println("调用有参函数了，咦咦咦.....");
		this.name = name;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
 
	public String say(String str) {
		return str;
	}
	@Override
	public String toString() {
		return "ReflectUser [name=" + name + ", addr=" + addr + "]";
	}
	
	

}
