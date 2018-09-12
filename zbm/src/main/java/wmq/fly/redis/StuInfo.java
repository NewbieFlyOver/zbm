package wmq.fly.redis;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import wmq.fly.validator.CaseMode;
import wmq.fly.validator.CheckCase;

public class StuInfo {
	
	private Integer id;

    private Integer teacherId;

    //@Length(min=1,max=3,message="姓名长度应为1~3个字")
    //@Pattern(regexp="[0-9]",message="应为中文名")
    //@NotBlank(message="用户名不能为空")
    private String stuName;

    //@NotEmpty(message="stuNo不能为空")
    private String stuNo;
    
    //@CheckCase(value = CaseMode.LOWER,message = "nickName必须是小写")
    private String nickName;
    
    private int stuGender;
        
    private List<StuInfo> stuInfoMList;
     
    private List<StuInfo> stuInfoFList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
    }

	public int getStuGender() {
		return stuGender;
	}

	public void setStuGender(int stuGender) {
		this.stuGender = stuGender;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
}