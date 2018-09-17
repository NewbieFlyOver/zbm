package wmq.fly.eventListener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *  方式二：传统方式配置事件监听器
 *  步骤如下：
 *  1. 自定义监听方法 SecondEvent
 *  2. 自定义事件监听器 secondEventHandle
 *
 */
//@Component
public class SecondEventListener  implements ApplicationListener<SecondEvent> {

	@Override
	public void onApplicationEvent(SecondEvent event) {
		System.out.println("I am "+ "SecondEventHandle");
		//业务逻辑处理
		event.printStr("hahahah");
		
	}

}
