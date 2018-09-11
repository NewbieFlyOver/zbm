package wmq.fly.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wmq.fly.mybatis.dao.TeacherInfoMapper;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService{
	
	@Autowired
	private TeacherInfoMapper teacherInfoMapper;

	@Override
	public Object getAllTeacherInfo() {
		return teacherInfoMapper.selectAllTeacherInfo();
	}
	
	@Override
	public Object getAllTeacherInfo01() {
		return teacherInfoMapper.selectAllTeacherInfo01();
	}
}
