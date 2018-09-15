package wmq.fly.junit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import wmq.fly.mybatis.entity.StuInfo;

/**
 * Controller层的单元测试
 *  参考博客：https://blog.csdn.net/sz85850597/article/details/80427408
 */

/*
 * 在ssm中的：
 * @RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class StuInfoTestController {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    private MockHttpSession session = new MockHttpSession();
    
    Map<Object, Object> map ;
    ObjectMapper mapper=new ObjectMapper();
    
   @Before
   public void befer() {
	   
	   mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();//这个很重要，初始化MockMvc对象
	   //登陆写在这里，并注入一个session，用于通过拦截器
   }
   
    @Test
    @Transactional //这样测试完数据就会回滚了，不会造成垃圾数据。如果你想关闭回滚，只要加上@Rollback(false)注解即可。@Rollback表示事务执行完回滚，支持传入一个参数value，默认true即回滚，false不回滚。
    public void insertStuInfo() throws Exception {
	   
    	map = new HashMap<Object, Object>();
    	map.put("stuName", "测试啊00");
    	map.put("stuNo", "12121");
    	String jsonString = mapper.writeValueAsString(map);
    	
    	/*StuInfo stuInfo = new StuInfo();
    	stuInfo.setStuName("测试啊00");
    	stuInfo.setStuNo("0123123");
    	String jsonString = mapper.writeValueAsString(stuInfo);*/
    	
    	mockMvc.perform(  //执行一个请求
    			MockMvcRequestBuilders.post("/test/stuInfo/insertStuInfo") //构造一个请求，Post请求就用.post方法
    			.contentType(MediaType.APPLICATION_JSON_UTF8)  //代表发送端发送的数据格式是application/json;charset=UTF-8，不加会报415。原因：应该是HttpRequester在发送请求的时候帮我们自己去做了一些处理，如果发送的是json数据自动帮我们加上了Content-Type字段的声明，从而能够正常返回结果。而在Java代码发送的时候，通过抓包我们发现是没有这个头字段的（当然没有，因为我们压根没有给他设置这个头字段）。所以我们要告诉接收方发送什么样的数据格式，如果没有告诉的话接收方可能就直接拒识了。
                .accept(MediaType.APPLICATION_JSON_UTF8) //代表客户端希望接受的数据类型为application/json;charset=UTF-8
    			.content(jsonString)  //请求参数（我这里是json格式的）
    			.session(session))   //注入一个session，这样拦截器才可以通过
    	.andExpect(  //添加执行完成后的断言
    			MockMvcResultMatchers.status().isOk()  //方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
    			)
    	//.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("13"))  //这里jsonPath用来获取id字段比对是否为2,不是就测试不通过
    	.andDo(MockMvcResultHandlers.print());//添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
    	
    	
    }
  
    @Test
    public void selectStuInfo() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.post("/stuInfo/getAllStuInfo01")
    			.accept(MediaType.APPLICATION_JSON_UTF8)
    			//.content(jsonString)
    			.session(session)
    			)
    	.andExpect(MockMvcResultMatchers.status().isOk())
    	.andDo(MockMvcResultHandlers.print());
    }
}
