package wmq.fly.mybatis.entity;

import java.util.List;

public class TeacherInfo {
    private Integer id;

    private String teacherName;

    private String teacherNo;
    
    private List<StuInfo> stuInfoList;
    
    private List<StuInfo> stuInfoMList;
     
    private List<StuInfo> stuInfoFList;

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
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

	public List<StuInfo> getStuInfoList() {
		return stuInfoList;
	}

	public void setStuInfoList(List<StuInfo> stuInfoList) {
		this.stuInfoList = stuInfoList;
	}

	public List<StuInfo> getStuInfoMList() {
		return stuInfoMList;
	}

	public void setStuInfoMList(List<StuInfo> stuInfoMList) {
		this.stuInfoMList = stuInfoMList;
	}

	public List<StuInfo> getStuInfoFList() {
		return stuInfoFList;
	}

	public void setStuInfoFList(List<StuInfo> stuInfoFList) {
		this.stuInfoFList = stuInfoFList;
	}
    
    
}