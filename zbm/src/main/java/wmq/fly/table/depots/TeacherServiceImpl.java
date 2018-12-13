package wmq.fly.table.depots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wmq.fly.mybatis.dao.TeacherInfoMapper;
import wmq.fly.mybatis.entity.TeacherInfo;

@Service
@Transactional(rollbackFor= {Exception.class})
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherInfoMapper teacherInfoMapper;

	/*
	 * 保存数据
	 */
	@Override
	public Object insertTeacher() {
		
		for(int i=0; i<10; i++) {
			QueryCondition t = new QueryCondition();
			t.setTeacherName("teacher"+i);
			t.setTableName("teacher_info");
			t.setTeacherNo("teacherNo"+i);
			//添加到主表中，并获取其id
			teacherInfoMapper.insertTeacher(t);
			//根据取模结果，将数据存放在对应的分表中
			t.setTableName("teacher_info"+(t.getId()%3+1));
			teacherInfoMapper.insertTeacher(t);
		}
		return "ok";
	}

	/*
	 * 从分表中查询数据
	 */
	@Override
	public Object selectTeacher(QueryCondition qc) {
		//根据取模结果，从对应的分表中获取数据
		qc.setTableName("teacher_info"+(qc.getId()%3+1));
		return teacherInfoMapper.selectByTeacherId(qc);
	}

}
