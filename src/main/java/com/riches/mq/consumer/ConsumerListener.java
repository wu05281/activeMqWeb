package com.riches.mq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.riches.mq.consumer.dto.User;


public class ConsumerListener implements MessageListener {

	private static Log log = LogFactory.getLog(ConsumerListener.class);

	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message instanceof ObjectMessage) {  
            ObjectMessage objMessage = (ObjectMessage) message;  
            try {  
                Object obj = objMessage.getObject();  
                User user = (User) obj;  
                System.out.println("���յ�һ��ObjectMessage������Email����");  
                System.out.println(user);  
//                if (1==1) {
//                	throw new RuntimeException("��������");
//                }
            } catch (JMSException e) {  
                e.printStackTrace();  
            }  
        }  
	}
}
