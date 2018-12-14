package wmq.fly.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
/*
 * springboot中自带的@Async注解，相当于将此方法变成一个多线程
 *  需要在启动类上添加@EnableAsync  //开启异步执行
 */

@Service
public class AsyncService {
	@Async //相当于将此方法变成一个多线程
	public void asyncTest() {
		System.out.println("####### 3 ######");
		for(int i=1;i<4;i++) {
			System.out.println("i: "+i);
		}
		System.out.println("####### 4 ######");
	}
	

}
