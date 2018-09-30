package wmq.fly.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "SwaggerController|一个用来测试swagger注解的控制器", tags= {"用户操作接口"})
public class SwaggerController {
	
	@ApiOperation(value="获取用户信息",tags={"获取用户信息copy"},notes="注意问题点")
	@RequestMapping(value="/swagger/test",method=RequestMethod.GET)
	@ApiImplicitParams({        
		@ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),        
		@ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),        
		@ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")    
	})
	public Object swaggerTest(@RequestParam(value="userId") Integer userId, @RequestParam(value="password") String password, 
            @RequestParam(value="newPassword") String newPassword) {
		
		return "password:" +password;
	}
}
