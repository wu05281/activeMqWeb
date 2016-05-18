package com.riches.mq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.riches.mq.consumer.dto.User;

/**
 * JMS������ ��Ϣ������ݶ��� ��Ϣ���� ������Ϣ����� ���յ�����Ϣ��*
 * <p>
 */
public class Consumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JMSException {
		System.out.println("��ʼ����Ϣ������");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		Destination destination = (Destination) applicationContext
				.getBean("destination");
		while (true) {
			User user = (User)template.receive(destination);
			if (null != user) {
				System.out.println("�ֶ��յ���Ϣ����Ϊ: " + user.getName());
			} else {
				break;
			}
		}
	}
}
