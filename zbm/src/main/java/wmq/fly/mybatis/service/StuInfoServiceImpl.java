package wmq.fly.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wmq.fly.mybatis.dao.StuInfoMapper;
import wmq.fly.mybatis.entity.StuInfo;

@Service
public class StuInfoServiceImpl implements StuInfoService {
	@Autowired
    private StuInfoMapper stuInfoMapper;
	
	public Object insertStuInfo(StuInfo stuInfo) {
		stuInfoMapper.insetStuInfo(stuInfo);
		return stuInfo.getId();
	}
	
	public Object getAllStuInfo() {
		return stuInfoMapper.selectAllStuInfo();
	}
	
	public Object getAllStuInfo01() {
		return stuInfoMapper.selectAllStuInfo01();
	}
	public Object getAllStuInfo02() {
		return stuInfoMapper.selectAllStuInfo02();
	}
	
	public Object getStuInfoById(StuInfo stuInfo) {
		return stuInfoMapper.selectStuInfoById(stuInfo);
	}
	
	
}
