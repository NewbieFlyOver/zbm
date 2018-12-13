package wmq.fly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import wmq.fly.eventListener.OneEventHandle;
import wmq.fly.eventListener.SecondEvent;
import wmq.fly.eventListener.SecondEventListener;
import wmq.fly.multipleDataSource.atomikos.DBConfig.DBConfig1;
import wmq.fly.multipleDataSource.atomikos.DBConfig.DBConfig2;

/**
 * 既可以使用springboot内嵌的tomcat，也可以打成war包放在外部的tomcat中运行
 * 
 * 打成war包放在外部的tomcat中运行的配置方法 ，参考博客：https://blog.csdn.net/u010598360/article/details/78789197/
 *  1. 在pom文件<project>下添加打包方式：
 *  	<packaging>war</packaging> 
 *  2. 在pom文件中添加外部tomcat依赖：
 *  	<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-tomcat</artifactId>
	        <scope>provided</scope>
		</dependency>
 *  3. 启动类继承SpringBootServletInitializer类并重写configure方法
 *  4. 打包（在pom文件同级目录中使用命令：mvn clean package 进行打包）并放在服务器中。
 *  5. 访问方式：http://localhost:[端口号]/[打包项目名]/
 * 注：即便项目是以上配置，依然可以用内嵌的tomcat来调试，启动命令和以前没变
 *
 */

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@MapperScan(basePackages = "wmq.fly.mybatis.dao") //扫描*Mapper.java类
//@EnableScheduling  //开启定时器
//@EnableCaching //开启redis的缓存
@EnableAsync  //开启异步执行
//读取springboot多数据源事务atomikos配置的文件信息
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class }) 
public class TomcatApplication  extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		 ConfigurableApplicationContext context = SpringApplication.run(TomcatApplication.class, args);
		 
		 //****************************** 事件监听器 *******************************************
		 //事件监听一：注解方式：发布OneEventHandle事件
		  context.publishEvent(new OneEventHandle(new Object(),"hello"));
		
		//事件监听二：传统方式
		 /*上下文对象
		 @Resource
		 private ApplicationContext context;*/
		 
		//装载事件到上下文容器中，此步可以在SecondEventHandle类上添加@Component注解将其添加到容器中。
		context.addApplicationListener(new SecondEventListener());
		//发布SecondEvent事件
		context.publishEvent(new SecondEvent("自定义", "测试事件."));
		
		 //关闭应用
	     //context.close();

	}
	
	/*
	 *  配置外部tommcat
	 * (non-Javadoc)
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	   // 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(TomcatApplication.class);
    }
	
	


	
}
