package wmq.fly.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 *  三种反射demo
 *
 */
public class ReflectionDemo {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, 
		IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		
		//forNameDemo();
		
		//getClassDemo();
		
		assignmentValueToPrivate();
	}
	
	/**
	 *  Class.forName() 的形式会装入类并做类的静态初始化，返回 Class 对象。
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	static void forNameDemo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		//创建此Class 对象所表示的类的一个新实例
		Class<?> forName = Class.forName("wmq.fly.reflection.ReflectUser");
		
		//调用了User的无参数构造方法.
		ReflectUser reflectUser = (ReflectUser) forName.newInstance();
		reflectUser.setName("铁军");
		reflectUser.setAddr("七连");
		System.out.println(reflectUser.toString());
		
		//调用了User的有参数构造方法.
		Constructor<?> constructor = forName.getConstructor(String.class,String.class);
		ReflectUser reflectUser2 = (ReflectUser) constructor.newInstance("小宁","七连");
		
	}
	
	/**
	 * .getClass() 的形式会对类进行静态初始化、非静态初始化，返回引用运行时真正所指的对象（因为子对象的引用可能会赋给父对象的引用变量中）所属的类的 Class 对象。
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	static void getClassDemo() throws NoSuchMethodException, SecurityException, IllegalAccessException, 
					IllegalArgumentException, InvocationTargetException {
		ReflectUser reflectUser = new ReflectUser();
		//调用了User的无参数构造方法.
		Class user = reflectUser.getClass();
		Method method = user.getMethod("say", String.class);
		Object invoke = method.invoke(reflectUser, "老刘");
		System.out.println(invoke);
	}
	
	static void assignmentValueToPrivate() throws ClassNotFoundException, InstantiationException, 
									IllegalAccessException, NoSuchFieldException, SecurityException {
		//创建此Class 对象所表示的类的一个新实例
		Class<?> forName = Class.forName("wmq.fly.reflection.ReflectUser");
		//获取当前类的所有属性
		Field[] fields = forName.getDeclaredFields();
		System.out.println("属性：");
		for(Field field : fields ) {
			System.out.println(field.getName());
		}
		
		//获取当前类的所有属性
		Method[] methods = forName.getDeclaredMethods();
		System.out.println("方法：");
		for(Method method : methods ) {
			System.out.println(method.getName());
		}
		
		//使用java的反射机制给私有属性赋值
		Object newInstance = forName.newInstance();
		Field fieldName = forName.getDeclaredField("name");
		//允许反射操作私有属性
		fieldName.setAccessible(true);
		fieldName.set(newInstance,"一乐");
		
		Field fieldAdrr = forName.getDeclaredField("addr");
		//允许反射操作私有属性
		fieldAdrr.setAccessible(true);
		fieldAdrr.set(newInstance,"下榕树");
		ReflectUser reflectUser = (ReflectUser)newInstance;
		System.out.println(reflectUser.toString());
	}
	
}
