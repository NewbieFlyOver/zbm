package wmq.fly.mq.activitymq.springboot.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 3000)
	public void send() {
		String reuslt = System.currentTimeMillis() + "---测试消息";
		System.out.println("reuslt:" + reuslt);
		jmsMessagingTemplate.convertAndSend(queue, reuslt);
	}

}
