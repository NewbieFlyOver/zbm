package wmq.fly.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmq.fly.mybatis.service.TeacherInfoService;

@RestController
public class TeacherInfoController {
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	@RequestMapping("/teacherInfo/getAllTeacherInfo")
	public Object getAllTeacherInfo() {
		return teacherInfoService.getAllTeacherInfo();
	}
	
	@RequestMapping("/teacherInfo/getAllTeacherInfo01")
	public Object getAllTeacherInfo01() {
		return teacherInfoService.getAllTeacherInfo01();
	}
}
