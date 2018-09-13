package wmq.fly.mybatis.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmq.fly.mybatis.entity.StuInfo;
import wmq.fly.mybatis.service.StuInfoService;

@RestController
@Validated
public class StuInfoController {
	
	@Autowired
	private StuInfoService stuInfoService;
	
	//MethodValidationPostProcessor;
	//普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
	//未验证：快速失败返回模式(只要有一个验证失败，则返回)
	//在@RequestBody StuInfo之间加注解 @Valid，然后后面加BindindResult即可；多个参数的，可以加多个@Valid和BindingResult
	@RequestMapping(value="/stuInfo/insertStuInfo")
	public Object insertStuInfo(@RequestBody @Valid StuInfo stuInfo,BindingResult result) {
		if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "字段校验未通过";
        }
		return stuInfoService.insertStuInfo(stuInfo);
	}
	
	@RequestMapping(value = "/stuInfo/getAllStuInfo")
	public Object getAllStuInfo() {
		return stuInfoService.getAllStuInfo();
	}
	
	@RequestMapping(value = "/stuInfo/getAllStuInfoA")
	public Object getAllStuInfoA() {
		return stuInfoService.getAllStuInfo();
	}
	
	@RequestMapping(value = "/stuInfo/getAllStuInfo01")
	public Object getAllStuInfo01() {
		System.out.println("11111");
		return stuInfoService.getAllStuInfo01();
	}
	@RequestMapping(value = "/stuInfo/getAllStuInfo02")
	public Object getAllStuInfo02() {
		return stuInfoService.getAllStuInfo02();
	}
}
