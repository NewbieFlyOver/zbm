package wmq.fly.multipleDataSource.test01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wmq.fly.multipleDataSource.test01.dao.UserMapper01;
import wmq.fly.multipleDataSource.test02.dao.UserMapper02;
import wmq.fly.multipleDataSource.test02.service.UserService02;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserService01 {
	
	@Autowired
	private UserMapper01 userMapper01;
	
	@Autowired
	private UserMapper02 userMapper02;
	
	@Autowired
	private UserService02 userService02;
	
	public String addUser03(String name, Integer age) {
		//事务进行回滚，因为事务的传播行为
		userMapper01.insert(name, age);
		userService02.addUser023(name, age);
		
		int i = 1/0;
		return "success";
	}
	
	public String addUser04(String name, Integer age) {
		//事务不会进行回滚，因为此时使用的是01数据源的事务，所有01添加失败，02添加成功
		userMapper01.insert(name, age);
		userMapper02.insert(name, age);
		
		int i = 1/0;
		return "success";
	}

}
