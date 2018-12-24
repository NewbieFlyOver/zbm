package wmq.fly.mq.activitymq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
//消费者
public class JmsReceiver {
	
	 public static void main(String[] args) throws JMSException {
			// 获取mq连接工程，ConnectionFactory ：连接工厂，JMS 用它创建连接
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
			// 创建连接， JMS 客户端到JMS Provider 的连接
			Connection createConnection = connectionFactory.createConnection();
			// 启动连接
			createConnection.start();
			// 创建会话工厂，一个发送或接收消息的线程， Boolean.FALSE表示不使用事务，Session.AUTO_ACKNOWLEDGE表示采用自动签收
			//此处session的模式要生产者与消费者设置相同
			Session session = createConnection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			// 创建队列
			Destination destination = session.createQueue("wmq_queue");
			//消费者，消息接收者
			MessageConsumer createConsumer = session.createConsumer(destination);
			while (true) {
				//监听消息
			TextMessage textMessage =(TextMessage) createConsumer.receive();
			if(textMessage!=null){
			   String text=	textMessage.getText();
			   System.out.println("消费者 获取到消息:  text:"+text);
			   
			   //session是指为手动签收时，这里要进行手动签收消息
			   //textMessage.acknowledge();
			   
			 //创建session的时候采用事务时(Boolean.TRUE)，这里要提交事务
			 //session.commit();
			}else{
				 break;
			}
			}
		
			System.out.println("消费者消费消息完毕!!!");
			//session.close();
			//connection.close();
		}
}