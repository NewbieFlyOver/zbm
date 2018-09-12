package wmq.fly.redis;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wmq.fly.mybatis.dao.StuInfoMapper;
import wmq.fly.mybatis.entity.StuInfo;

@Transactional(rollbackFor= {Exception.class})
@Service
public class StuInfoRedisServiceImpl implements StuInfoRedisService{
	@Autowired
	private StuInfoMapper stuInfoMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private KeyGenerator key;
	
	public StuInfo selectStuInfoById(StuInfo stuInfo){
		System.out.println("***********"+key.toString());
		//redisUtils.set(key.toString(),key,10L,TimeUnit.MINUTES);
		StuInfo reStuInfo = null;
		//如果缓存存在
        boolean hasKey = redisUtils.exists(RedisKeyPrefix.STUINFO+stuInfo.getId());
        if(hasKey){
            //获取缓存
            reStuInfo =  (StuInfo)redisUtils.get(RedisKeyPrefix.STUINFO+stuInfo.getId());
            System.out.println("从缓存获取的数据"+ reStuInfo);
        }else{
            //从DB中获取信息
        	 System.out.println("从数据库中获取数据");
        	 reStuInfo = stuInfoMapper.selectStuInfoById(stuInfo);//根据ID查询，
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(RedisKeyPrefix.STUINFO+stuInfo.getId(),reStuInfo,10L,TimeUnit.MINUTES);
            System.out.println("数据插入缓存"+reStuInfo.toString());
        }
		return reStuInfo;
	}
}
