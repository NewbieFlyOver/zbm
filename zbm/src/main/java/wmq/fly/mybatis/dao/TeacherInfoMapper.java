package wmq.fly.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import wmq.fly.mybatis.entity.TeacherInfo;
import wmq.fly.table.depots.QueryCondition;

@Repository
public interface TeacherInfoMapper {
	
	List<TeacherInfo> selectAllTeacherInfo();
	
	List<TeacherInfo> selectAllTeacherInfo01();
	
	//添加
	int insertTeacher(QueryCondition queryCondition);
	
	//查询
	TeacherInfo selectByTeacherId(QueryCondition queryCondition);

}