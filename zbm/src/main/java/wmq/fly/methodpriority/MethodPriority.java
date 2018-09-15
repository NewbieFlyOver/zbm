package wmq.fly.methodpriority;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 利用反射对方法进行动态排序执行
 */
public class MethodPriority {
	
	public String runAliPay(Car car, String str) {
		System.out.println(car.toString() + "runAliPay");
		car.setName("奥迪");
		System.out.println(car.toString() + "runAliPay");
		return str+"runAliPay";
	}
	
	public String runWechatPay(Car car,  String str) {
		System.out.println(car.toString() + "runWechatPay");
		car.setName("红旗");
		System.out.println(car.toString() + "runWechatPay");
		return str+"runWechatPay";
	}
	
	public String runBalancePay(Car car,  String str) {
		System.out.println(car.toString() + "runBalancePay");
		car.setName("宝马");
		System.out.println(car.toString() + "runWechatPay");
		return str+"runAliPay";
	}
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//传入的参数
		Car car = new Car();
		car.setName("大奔");
		car.setType("小型车");
		
		//实例化将要调用方法的所在类
		MethodPriority methodPriority = new MethodPriority();
		
		//方法名
		String[] methodName = {"runAliPay", "runWechatPay", "runBalancePay"};
		//方法排序
		Integer[] methodPriorityArr = {2,0,1};
		//利用反射按方法排序调用对应的方法
		for(Integer i : methodPriorityArr) {
			Method method = methodPriority.getClass().getMethod(methodName[i], Car.class, String.class);
			Object invoke = method.invoke(methodPriority, car, i.toString());
			System.out.println(invoke);
		}	
	}
}
