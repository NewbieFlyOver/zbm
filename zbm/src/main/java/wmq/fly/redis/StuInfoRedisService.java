package wmq.fly.redis;

import wmq.fly.mybatis.entity.StuInfo;

public interface StuInfoRedisService {
	
	StuInfo selectStuInfoById(StuInfo stuInfo);

}
