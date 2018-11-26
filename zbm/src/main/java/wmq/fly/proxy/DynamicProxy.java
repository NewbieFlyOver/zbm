package wmq.fly.proxy;



import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * JDK动态代理
 *
 */
//代理接口
interface CarInterface{
	public void say();
}
//代理对象
class BMCar implements CarInterface {
	public void say() {
		System.out.println("汽车行驶中........");
	}
}


//JDK动态代理
class JdkProxy implements java.lang.reflect.InvocationHandler{
	private Object ob;
	JdkProxy(Object ob){
		this.ob = ob;
	}
	
	@Override
	public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
		System.out.println("启动喽~~~~~");
		Object invoke = paramMethod.invoke(ob, paramArrayOfObject);
		System.out.println("停车哈**********");
		return invoke;
	}
}

//cjlib动态代理
class CjlibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object object, Method methd, Object[] paramArrary, MethodProxy methodProxy) throws Throwable {
		System.out.println("启动喽~~~~~");
		Object invoke = methodProxy.invoke(object, paramArrary);
		System.out.println("停车哈**********");
		return invoke;
	}
	
}


public class DynamicProxy {

	public static void jbkProxy() {
		BMCar bMCar = new BMCar();
		JdkProxy jdkProxy = new JdkProxy(bMCar);
		CarInterface car =(CarInterface) Proxy.newProxyInstance(bMCar.getClass().getClassLoader(), bMCar.getClass().getInterfaces(), jdkProxy);
		car.say();
	}
	
	public static void cjlibProxy() {
		/*Cglib cglib = new Cglib();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(BMCar.class);
		enhancer.setCallback(cglib);
		CarInterface car = (CarInterface) enhancer.create();
		car.say();*/
	}
	
	
	public static void main(String[] args) {
		jbkProxy();
		

	}
}
