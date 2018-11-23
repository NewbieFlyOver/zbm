package wmq.fly.jvmTuning;

import java.text.DecimalFormat;

/**
 * jvm调优
 *
 */
public class JvmTuning {
	public static void main(String[] args) throws InterruptedException {
		jvm02();

	}
	
	//设置新生代比例参数
	//参数: -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
	
	//设置新生与老年代代参数
	//参数: -Xms20m -Xmx20m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:NewRatio=2
	private static void jvm02() {
		 byte [] b = null;
		 for (int i = 0; i < 10; i++) {
			b =new byte[1*1024];
			System.out.println(i);
		}
	}
	
	
	//设置最大堆内存
	//参数: -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
	private void jvm01() throws InterruptedException {
		byte[] b1 = new byte[1 * 1024 * 1024];
		System.out.println("分配了1m");
		jvmInfo();		
		Thread.sleep(3000);
		byte[] b2 = new byte[4 * 1024 * 1024];
		System.out.println("分配了4m");
		Thread.sleep(3000);
		jvmInfo();
	}
	
	
	static private String toM(long maxMemory) {
		float num = (float) maxMemory / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
		String s = df.format(num);// 返回的是String类型
		return s;
	}
	static private void jvmInfo() {
		// 最大内存
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("maxMemory（最大内存）:" + maxMemory + ",转换为M:" + toM(maxMemory));
		// 当前空闲内存
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory（当前空闲内存）:" +freeMemory+",转换为M:"+toM(freeMemory));
		// 已经使用内存
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemory（已经使用内存）:" +totalMemory+",转换为M："+toM(totalMemory));
	}
	
	
}
