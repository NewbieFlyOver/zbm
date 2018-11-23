package wmq.fly.gc;

public class GcDemo {

	public static void main(String[] args) {
		GcDemo gc = new GcDemo();
		//不可达：此对象没有在使用或被其他对象引用；可达：对象在使用或被其他对象引用
		//将对象设置为不可达，垃圾回收机制将对其进行回收
		gc = null;
		//手动调用垃圾回收机制，不一定会调用
		System.gc();
	}
	
	//垃圾回收机制之前调用
	@Override
	protected void finalize() throws Throwable {
		System.out.println("垃圾回收机制之前调用......");
		super.finalize();
	}
}
