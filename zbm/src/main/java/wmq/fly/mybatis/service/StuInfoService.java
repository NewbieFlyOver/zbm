package wmq.fly.mybatis.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wmq.fly.mybatis.entity.StuInfo;

public interface StuInfoService {
	
	Object getAllStuInfo();
	
	Object getAllStuInfo01();
	
	Object getAllStuInfo02();

	Object insertStuInfo(StuInfo stuInfo);
	
	Object getStuInfoById(StuInfo stuInfo);
	
	 Object writeExcel(HttpServletResponse response);
	 
	 Object echart(HttpServletResponse response);
	 
	 String freemarker(HttpServletRequest request,HttpServletResponse response) ;

}
