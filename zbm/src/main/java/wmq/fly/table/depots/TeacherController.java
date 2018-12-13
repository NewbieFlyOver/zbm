package wmq.fly.table.depots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	
	//添加
	@RequestMapping("/insertTeacher")
	public Object insertTeacher() {
		return teacherService.insertTeacher();
	}
	
	//查询
	@RequestMapping("/selectTeacher")
	public Object selectTeacher(@RequestBody QueryCondition qc) {
		return teacherService.selectTeacher(qc);
	}
}
