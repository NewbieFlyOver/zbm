package wmq.fly.mybatis.service;

import wmq.fly.mybatis.entity.StuInfo;

public interface StuInfoService {
	
	Object getAllStuInfo();
	
	Object getAllStuInfo01();
	
	Object getAllStuInfo02();

	Object insertStuInfo(StuInfo stuInfo);
	
	Object getStuInfoById(StuInfo stuInfo);
	

}
