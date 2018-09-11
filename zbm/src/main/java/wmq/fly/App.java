package wmq.fly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@MapperScan(basePackages = "wmq.fly.mybatis.dao") //扫描*Mapper.java类
//@EnableScheduling  //开启定时器
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
