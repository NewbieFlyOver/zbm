package wmq.fly.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmq.fly.mybatis.entity.StuInfo;
import wmq.fly.mybatis.service.StuInfoService;


@RestController
public class StuInfoJunitController {
	
	@Autowired
	private StuInfoService stuInfoService;
	
	//添加
	@RequestMapping(value="/test/stuInfo/insertStuInfo")
	public Object insertStuInfo(@RequestBody StuInfo stuInfo) {
		System.out.println("000000000");
		return stuInfoService.insertStuInfo(stuInfo);
	}
	
//注：Junit测试类 StuInfoTestController和StuInfoTestService 在src/test/java下的wmq.fly.junit中
}
