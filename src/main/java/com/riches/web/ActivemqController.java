package com.riches.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riches.mq.consumer.dto.User;
import com.riches.mq.provider.QueueSender;

@Controller
public class ActivemqController {
	
	Logger log = LoggerFactory.getLogger(ActivemqController.class);
     
    @Resource QueueSender queueSender;
    
//    @Resource SpeakInterface speakInterface;
    
    @RequestMapping("/")
	public String index(HttpServletRequest request) {
//    	People  pe = new People();
//    	pe.setAge(22);
//    	pe.setSex(0);
//    	System.out.println(speakInterface.speak(pe));
    	log.info("index");
    	return "forward:/second";
    }
    
    @RequestMapping("/second")
   	public String forward(HttpServletRequest request) {
//       	People  pe = new People();
//       	pe.setAge(22);
//       	pe.setSex(0);
//       	System.out.println(speakInterface.speak(pe));
       	log.info("second");
       	return "index";
       }
     

    @ResponseBody
    @RequestMapping("/queueSender")
    public String queueSender(@RequestParam("message")String message){
    	log.info("发送原始报文：{}", message);
        String opt="";
        try {
        	User user = new User();
			user.setId("1");
			user.setName(message);
            queueSender.send(user);
            opt="suc";
        } catch (Exception e) {
            opt=e.getCause().toString();
        }
        return opt;
    }
}