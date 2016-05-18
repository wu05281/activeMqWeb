package com.riches.mq.provider;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.riches.mq.consumer.dto.User;

public class Producers {
	/**
	 * @param args
	 *            jmsTemplate和destination都是在spring配置文件中进行配制的
	 *            Sender只使用了配置文件中的jmsFactory，jmsTemplate，还有destination这三个属性
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		Destination destination = (Destination) applicationContext
				.getBean("destination");
		template.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
//				return session
//						.createTextMessage("发送消息：Hello ActiveMQ Text Message2！");
				User user = new User();
				user.setId("1");
				user.setName("webber");
				return session.createObjectMessage(user);
			}
		});
		System.out.println("成功发送了一条JMS消息");
	}
}
