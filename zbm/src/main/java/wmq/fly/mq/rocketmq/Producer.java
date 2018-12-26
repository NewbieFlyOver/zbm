package wmq.fly.mq.rocketmq;

import java.util.Currency;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 生产者，使用rocketmq集群
 */
public class Producer {

	public static void main(String[] args) throws MQClientException {
		//分组
		DefaultMQProducer producer = new DefaultMQProducer("wmq-group");
		//集群的服务器地址
		producer.setNamesrvAddr("192.168.163.133:9876;192.168.163.137:9876");
		producer.setInstanceName("producer");
		producer.start();
		try {
			for (int i = 0; i < 1; i++) {
				Thread.sleep(1000); // 每秒发送一次MQ
				Message msg = new Message("wmq-fly02-topic", // topic 主题名称
						"tagA", // tag 临时值
						("wmq-fly02-"+i).getBytes()// body 内容
				);
				String keys = Long.toString(System.currentTimeMillis());
				msg.setKeys(keys);
				
				SendResult sendResult = producer.send(msg);
				System.out.println("keys: "+keys+ sendResult.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//producer.shutdown();
	}

}