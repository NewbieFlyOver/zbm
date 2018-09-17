package wmq.fly.eventListener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
/**
 *  方式一：注释方式配置事件监听器
 *
 */

@Component
public class OneEventHandle {
	
	
	public OneEventHandle() {}
	
	public OneEventHandle(Object object,String str) {
		//System.out.println(str);
	}

  /**
      * 参数任意(为Object）的时候所有事件都会监听到
      *  所有，该参数事件，或者其子事件（子类）都可以接收到
      
   *  Object为指定类的时候，只监听对应的类
   */
    @EventListener//(condition="#event.flag")
    public void event(SecondEvent event){
		//System.out.println("欢迎来打事件监听类~~~~~~~");
        System.out.println("OneEventHandle 接收到事件：" + event.getClass());
    }

}
