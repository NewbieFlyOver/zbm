package wmq.fly.multipleDataSource.test01.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import wmq.fly.multipleDataSource.entity.User;
/**
 * 使用ehcache缓存需要在启动类上添加@EnableCaching，用以开始缓存
 *
 */

//@CacheConfig(cacheNames="BaseCache")
public interface UserMapper01 {
	
	//@Cacheable
	@Select("SELECT * FROM USERS WHERE NAME = #{name}")
	User findByName(@Param("name") String name);
	
	@Insert("INSERT INTO USERS(NAME, AGE) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);
	
}