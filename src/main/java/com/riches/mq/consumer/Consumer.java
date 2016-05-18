package com.riches.mq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.riches.mq.consumer.dto.User;

/**
 * JMS消费者 消息题的内容定义 消息对象 接收消息对象后： 接收到的消息体*
 * <p>
 */
public class Consumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JMSException {
		System.out.println("初始化消息消费者");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-mq.xml");
		JmsTemplate template = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");
		Destination destination = (Destination) applicationContext
				.getBean("destination");
		while (true) {
			User user = (User)template.receive(destination);
			if (null != user) {
				System.out.println("手动收到消息内容为: " + user.getName());
			} else {
				break;
			}
		}
	}
}
