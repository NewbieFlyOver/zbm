package wmq.fly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmq.fly.mybatis.entity.StuInfo;

@RestController
public class StuInfoRedisController {
	
	@Autowired
	private StuInfoRedisService stuInfoRedisService;
	
	@RequestMapping(value="/stuInfoRedis/selectStuInfoById")
	StuInfo selectStuInfoById(@RequestBody StuInfo stuInfo) {
		return stuInfoRedisService.selectStuInfoById(stuInfo);
	}

}
