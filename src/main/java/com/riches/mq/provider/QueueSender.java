package com.riches.mq.provider;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
     
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
    
    @Autowired
    @Qualifier("destination")
    private Destination destination;
     
    public void send(final Object object){
    	log.info("调用发送消息msg：{}", object);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage((Serializable) object);
            }
        });
    }
}
