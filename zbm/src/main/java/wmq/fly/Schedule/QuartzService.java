package wmq.fly.Schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 有两种开启方式：1. 在App.java中添加注解@EnableScheduling。 2. 自定义一个类在类级添加@Configuration 和 @EnableScheduling 注解如Timer类
 *
 */
@Component
public class QuartzService {
	
	@Scheduled(cron = "0/1 * * * * ?")
    public void timerToNow(){
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
