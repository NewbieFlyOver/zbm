package wmq.fly.xxljob.jobhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;


/**
 * - 1、 新建一个继承com.xxl.job.core.handler.IJobHandler的Java类； - 2、
 * 该类被Spring容器扫描为Bean实例，如加“@Component”注解； - 3、 添加
 * “@JobHander(value="自定义jobhandler名称")”注解，注解的value值为自定义的JobHandler名称，
 * 该名称对应的是调度中心新建任务的JobHandler属性的值。 （可参考Sample示例执行器中的DemoJobHandler，见下图）
 * 
 * @author Administrator
 *
 */

/**
 *   ！！！！！！！！！！！配置文件及pom文件见resource下的xxljob文件夹
 *
 */
@JobHander("flyJobHandler")
@Service
public class FlyJobHandler extends IJobHandler{

	@Value("${xxl.job.executor.port}")
	private String port;

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		//paras是用来接收执行参数的
		System.out.println("flyJobHandler### port:" + port + "---" + System.currentTimeMillis());
		return ReturnT.SUCCESS;
	}

}
