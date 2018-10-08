package wmq.fly.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({StuInfoTestController.class,StuInfoTestService.class})
public class StuInfoSuits {
	//不用写代码，只需要注解即可
	
	/*
	项目开发完后，我们写了100个测试用例类，我不能每个类都点击进去，然后慢慢执行，
	SpringBoot提供了打包测试的方式：我们用一个类，把所有的测试类整理进去，然后直接运行这个类，所有的测试类都会执行。
	此处将StuInfoTestController,StuInfoTestService类打包进来
	*/
}
