package wmq.fly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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
public class TomcatApplication  extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	   // 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(TomcatApplication.class);
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(TomcatApplication.class, args);
	}
}
