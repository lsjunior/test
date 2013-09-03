package net.woodstock.rockapi.ejb.test;

import java.io.InputStream;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

public class TestJMS {

	private static void testQueue() throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = TestJMS.class.getClassLoader().getResourceAsStream("ejb-client.properties");
		properties.load(inputStream);
		Context context = new InitialContext(properties);

		Queue destination = (Queue) context.lookup("queue/testQueue");

		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("QueueConnectionFactory");

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Message message = session.createTextMessage();
		((TextMessage) message).setText("Queue 1");

		MessageProducer producer = session.createProducer(destination);
		producer.send(message);

		session.close();
		connection.close();
	}

	private static void testTopic() throws Exception {
		Properties properties = new Properties();
		InputStream inputStream = TestJMS.class.getClassLoader().getResourceAsStream("ejb-client.properties");
		properties.load(inputStream);
		Context context = new InitialContext(properties);

		Topic destination = (Topic) context.lookup("topic/testTopic");

		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("TopicConnectionFactory");

		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Message message = session.createTextMessage();
		((TextMessage) message).setText("Topic 1");

		MessageProducer producer = session.createProducer(destination);
		producer.send(message);

		session.close();
		connection.close();
	}

	public static void main(String[] args) {
		try {
			testQueue();
			testTopic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
