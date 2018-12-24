package wmq.fly.mq.activitymq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

//生产者
public class Producter {
	
	public static void main(String[] args) throws JMSException {
		// 获取mq连接工程， 连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接，JMS 客户端到JMS Provider 的连接
		Connection createConnection = connectionFactory.createConnection();
		// 启动连接
		createConnection.start();
		// 创建会话工厂， Session： 一个发送或接收消息的线程，Boolean.FALSE表示不使用事务，Session.AUTO_ACKNOWLEDGE表示采用自动签收
		//此处session的模式要生产者与消费者设置相同
		Session session = createConnection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		// 创建队列， 消息的目的地;消息发送给谁
		Destination destination = session.createQueue("wmq_queue");
		// MessageProducer：消息生产者
		MessageProducer producer = session.createProducer(destination);
		// 不持久化:DeliveryMode.NON_PERSISTENT;  持久化：DeliveryMode.PERSISTENT
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		for (int i = 1; i <= 5; i++) {
			System.out.println("我是生产者: " + i);
			sendMsg(session, producer, "我是生产者: " + i);

			//创建session的时候采用事务时(Boolean.TRUE)，这里要提交事务
			//session.commit();
		}
		System.out.println("生产者 发送消息完毕!!!");
	}

	/**
	 * 在指定的会话上，通过指定的消息生产者发出一条消息
	 * 
	 * @param session
	 *            消息会话
	 * @param producer
	 *            消息生产者
	 */
	public static void sendMsg(Session session, MessageProducer producer, String i) throws JMSException {
		// 创建一条文本消息
		TextMessage textMessage = session.createTextMessage("hello activemq " + i);
		// 通过消息生产者发出消息
		producer.send(textMessage);
	}
}