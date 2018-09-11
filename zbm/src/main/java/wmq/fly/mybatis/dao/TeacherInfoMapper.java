package wmq.fly.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import wmq.fly.mybatis.entity.TeacherInfo;

@Repository
public interface TeacherInfoMapper {
	
	List<TeacherInfo> selectAllTeacherInfo();
	
	List<TeacherInfo> selectAllTeacherInfo01();
	

}