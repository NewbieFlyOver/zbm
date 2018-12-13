package wmq.fly.table.depots;

public class QueryCondition {
	
    private Integer id;

    private String teacherName;

    private String teacherNo;
	
	private String tableName;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	@Override
	public String toString() {
		return "QueryCondition [id=" + id + ", teacherName=" + teacherName + ", teacherNo=" + teacherNo + ", tableName="
				+ tableName + "]";
	}
	

}
