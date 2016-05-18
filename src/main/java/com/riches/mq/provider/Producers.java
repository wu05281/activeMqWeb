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
	 *            jmsTemplate��destination������spring�����ļ��н������Ƶ�
	 *            Senderֻʹ���������ļ��е�jmsFactory��jmsTemplate������destination����������
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
//						.createTextMessage("������Ϣ��Hello ActiveMQ Text Message2��");
				User user = new User();
				user.setId("1");
				user.setName("webber");
				return session.createObjectMessage(user);
			}
		});
		System.out.println("�ɹ�������һ��JMS��Ϣ");
	}
}
