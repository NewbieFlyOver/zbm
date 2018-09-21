package wmq.fly.redis;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StuInfoRedisControllerTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	 @Autowired
	 private RedisTemplate redisTemplate;
	
//*********************  String  ********************	
	@Test
	public void setRedisString() {
		ValueOperations opsForValue = redisTemplate.opsForValue();
		opsForValue.set("str1", "hello", 10, TimeUnit.MINUTES);
	}
	
	@Test
	public void getRedisString() {
		ValueOperations opsForValue = redisTemplate.opsForValue();
		Object object = opsForValue.get("str1");
		Assert.assertEquals(object, "hello");
	}
	
//*********************  List  ********************	
	@Test
	public void setRedisList() {
		String[] strAarray= {"every","people","very","good"};
		List<String> list = Arrays.asList(strAarray);
        ListOperations opsForList = redisTemplate.opsForList();
        Long rightPush = opsForList.rightPush("list", list);
        System.out.println(rightPush);
	}
	
	@Test
	public void getRedisList() {
		ListOperations opsForList = redisTemplate.opsForList();
		List range = opsForList.range("list", 0, 9);
		System.out.println(range.toString());
	}
	
//*********************  set：无序 ********************	
	@Test
	public void setRedisSet() {
		SetOperations opsForSet = redisTemplate.opsForSet();
		opsForSet.add("set", "FirstSet");
		Long add = opsForSet.add("set", "SecondSet");
		System.out.println(add);
	}
	
	@Test
	public void getRedisSet() {
		SetOperations opsForSet = redisTemplate.opsForSet();
		Set members = opsForSet.members("set");
		
		Iterator<String> it = members.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();  
		  System.out.println(str);  
		}  
	}

//*********************  zset：有序  ********************	
	@Test
	public void setRedisZset() {
		ZSetOperations opsForZSet = redisTemplate.opsForZSet();
		Boolean add = opsForZSet.add("zset", "firstZset", 1);
		opsForZSet.add("zset", "secondeZset", 0);
		System.out.println(add);
	}
	
	@Test
	public void getRedisZset() {
		ZSetOperations opsForZSet = redisTemplate.opsForZSet();
		Set set = opsForZSet.range("zst", 0, 10);
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Object next = iterator.next();
			System.out.println(next);
		}
	}
	
//*********************  hash  ********************		
	@Test
	public void setReidsHash() {
		HashOperations opsForHash = redisTemplate.opsForHash();
		opsForHash.put("hash", "hash", "firstHash");
		opsForHash.put("hash", "secondhash", "firstHash");
		opsForHash.put("secondhash", "secondhash", "secondHash");
	}
	
	@Test
	public void getReidsHash() {
		HashOperations opsForHash = redisTemplate.opsForHash();
	    Map<String, String> map = opsForHash.entries("hash");
	    //第一种方式遍历map
	    Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<String, String> entry = it.next();
	    	System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	     }
	    System.out.println("*************************************************");
	    //第二种方式遍历map，推荐，尤其是容量大时
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	    	           //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
	                 //entry.getKey() ;entry.getValue(); entry.setValue();
	    	             //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
	    	            System.out.println("key= " + entry.getKey() + " and value= "
	    	                     + entry.getValue());
	    }
	}
	
	@Test
	public void bigDecimal() {
		BigDecimal bd = new BigDecimal(10);
		int num = Integer.valueOf(bd.toString());
		System.out.println(num);
	}
}
