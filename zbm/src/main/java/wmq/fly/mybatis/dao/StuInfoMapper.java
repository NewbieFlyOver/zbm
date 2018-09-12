package wmq.fly.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import wmq.fly.mybatis.entity.StuInfo;

@Repository
public interface StuInfoMapper {
	
    List<StuInfo> selectAllStuInfo();
    
    List<StuInfo> selectAllStuInfo01();
    
    List<StuInfo> selectAllStuInfo02();
    
    int insetStuInfo(StuInfo stuInfo);
    
    StuInfo selectStuInfoById(StuInfo stuInfo);
}