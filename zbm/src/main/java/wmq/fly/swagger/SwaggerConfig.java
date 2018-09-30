package wmq.fly.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot集成swagger，接口的文档在线自动生成，完成功能测试。
 * 整合swagger参考博客：https://www.imooc.com/article/47344
 * 常用注解：https://blog.csdn.net/u014231523/article/details/76522486			
 *
 */

/**
 * Swagger简介：
 *Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful
 * 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。
 * 文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
 * 
 */

@Configuration
@EnableSwagger2 //开启swagger
public class SwaggerConfig {    
	@Bean     
	public Docket buildDocket() {        
		return new Docket(DocumentationType.SWAGGER_2)  
				//要扫描的API(Controller)基础包，记住一定要改成自己的         
				//要扫描的API(Controller)基础包，记住一定要改成自己的         
				//要扫描的API(Controller)基础包，记住一定要改成自己的     
				.select().apis(RequestHandlerSelectors.basePackage("wmq.fly.swagger"))  
				.paths(PathSelectors.any()) // and by paths                 
				.build()                 
				.apiInfo(buildApiInf());     
		}    
	private ApiInfo buildApiInf() {        
		return new ApiInfoBuilder()                 
				.title("Spring Boot中使用Swagger2 UI构建API文档")                 
				.contact("test")                
				.version("1.0.0")                
				.build();     
	}
}
