package wmq.fly.multipleDataSource.test02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wmq.fly.multipleDataSource.test01.dao.UserMapper01;
import wmq.fly.multipleDataSource.test02.dao.UserMapper02;

@Service
public class UserService02 {
	
	@Autowired
	private UserMapper02 userMapper02;
	
	@Transactional
	public String addUser023(String name, Integer age) {
		userMapper02.insert(name, age);
		return "success";
	}

}
