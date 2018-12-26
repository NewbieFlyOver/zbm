package wmq.fly.mq.rocketmq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 消费者，使用rocketmq集群
 *	   包含幂等问题的处理
 */
public class Consumer {
	//模拟Redis存储被消费的信息
	static Map<String,String> map = new HashMap<String,String>();
	
	public static void main(String[] args) throws MQClientException {
		//分组
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("wmq-group");
		//集群的服务器地址
		consumer.setNamesrvAddr("192.168.163.133:9876;192.168.163.137:9876");
		consumer.setInstanceName("consumer");
		consumer.subscribe("wmq-fly02-topic", "tagA");

		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				
				String keys = null;
				String msgId = null;
				for (MessageExt msg : msgs) {
					try {
						keys = msg.getKeys();
						msgId = msg.getMsgId();
						if(map.containsKey(keys)) {
							System.out.println("已被消费； keys: "+keys+", msgId: "+msgId+", body: "+msg.getBody());
							//返回消费成功，防止继续重试
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
						
						//正常的业务代码
						System.out.println(" keys: "+keys+", msgId: "+msgId+", body: "+msg.getBody());
						
					} finally {
						map.put(keys, msgId);
					}
				}
				//用异常模拟网络延时，进行重试
				int i= 1/0;
				
				//消费成功
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.println("Consumer Started.");
		//consumer.shutdown();
	}
}