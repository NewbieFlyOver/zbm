package wmq.fly.multipleDataSource.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmq.fly.multipleDataSource.entity.User;
import wmq.fly.multipleDataSource.test01.dao.UserMapper01;
import wmq.fly.multipleDataSource.test01.service.UserService01;
import wmq.fly.multipleDataSource.test02.dao.UserMapper02;
import wmq.fly.multipleDataSource.test02.service.UserService02;

@RestController
public class UserMulController {
	private static Logger log = Logger.getLogger(UserMulController.class);
	
	@Autowired
	private UserMapper01 userMapper01;
	
	@Autowired
	private UserMapper02 userMapper02;
	
	@Autowired
	private UserService01 userService01;
	
	@Autowired
	private UserService02 userService02;
	
	@RequestMapping("/addUser01")
	public String addUser01(String name, Integer age) {
		log.info("###Info#### name:"+name);
		userMapper01.insert(name, age);
		return "success";
	}
	
	@RequestMapping("/addUser02")
	public String addUser02(String name, Integer age) {
		userMapper02.insert(name, age);
		return "success";
	}
	
    //用于测试多数据源的事务
	@RequestMapping("/addUser03")
	public String addUser03(String name, Integer age) {
		userService01.addUser03(name, age);
		return "success";
	}
	
    //用于测试多数据源的事务
	@RequestMapping("/addUser04")
	public String addUser04(String name, Integer age) {
		userService01.addUser04(name, age);
		return "success";
	}
	
	@RequestMapping("/findByName")
	public User findByName(String name) {
		return userMapper01.findByName(name);
	}
	
}
