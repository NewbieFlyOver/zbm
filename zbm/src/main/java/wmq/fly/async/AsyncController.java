package wmq.fly.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
	
	//从application.yml中读取自定义参数
	@Value("${name}")
	private String name;

	@Autowired
	private AsyncService asyncService;
	
	@RequestMapping("/asyncTest")
	public String asyncTest() {
		System.out.println("####### 1 ######");
		asyncService.asyncTest();
		System.out.println("####### 2 ######");
		return "success";
	}
	
	@RequestMapping("/getName")
	public String getName() {
		return name;
	}
}
