package wmq.fly.multipleDataSource.test01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wmq.fly.multipleDataSource.test01.dao.UserMapper01;
import wmq.fly.multipleDataSource.test02.dao.UserMapper02;
import wmq.fly.multipleDataSource.test02.service.UserService02;

@Service
@Transactional
public class UserService01 {
	
	@Autowired
	private UserMapper01 userMapper01;
	
	@Autowired
	private UserMapper02 userMapper02;
	
	@Autowired
	private UserService02 userService02;
	
	public String addUser03(String name, Integer age) {
		//由于test01与test02数据源通过jpa+automikos使用同一个事务，所以会进行事务回滚
		userMapper01.insert(name, age);
		userService02.addUser023(name, age);
		
		//int i = 1/0;
		return "success";
	}
	
	public String addUser04(String name, Integer age) {
		//由于test01与test02数据源通过jpa+automikos使用同一个事务，所以会进行事务回滚
		userMapper01.insert(name, age);
		userMapper02.insert(name, age);
		
		int i = 1/0;
		return "success";
	}

}
