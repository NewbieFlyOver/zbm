package wmq.fly.table.depots;

import org.springframework.web.bind.annotation.RequestBody;

public interface TeacherService {
	Object insertTeacher();
	Object selectTeacher(QueryCondition qc);

}
