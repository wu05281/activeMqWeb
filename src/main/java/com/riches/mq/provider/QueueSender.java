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
    private JmsTemplate jmsTemplate;//通过@Qualifier修饰符来注入对应的bean
    
    @Autowired
    @Qualifier("destination")
    private Destination destination;//通过@Qualifier修饰符来注入对应的bean
     
    /**
     * 发送一条消息到指定的队列（目标）
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void send(final Object object){
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage((Serializable) object);
            }
        });
    }
}
