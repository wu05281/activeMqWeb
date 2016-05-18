package com.riches.mq.provider;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {
     
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;//ͨ��@Qualifier���η���ע���Ӧ��bean
    
    @Autowired
    @Qualifier("destination")
    private Destination destination;//ͨ��@Qualifier���η���ע���Ӧ��bean
     
    /**
     * ����һ����Ϣ��ָ���Ķ��У�Ŀ�꣩
     * @param queueName ��������
     * @param message ��Ϣ����
     */
    public void send(final Object object){
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage((Serializable) object);
            }
        });
    }
}
