package com.riches.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jikexueyuan.rpc.People;
import com.jikexueyuan.rpc.SpeakInterface;
import com.riches.mq.consumer.dto.User;
import com.riches.mq.provider.QueueSender;

@Controller
public class ActivemqController {
     
    @Resource QueueSender queueSender;
    
    @Resource SpeakInterface speakInterface;
    
    @RequestMapping("/")
	public String index(HttpServletRequest request) {
    	People  pe = new People();
    	pe.setAge(22);
    	pe.setSex(0);
    	System.out.println(speakInterface.speak(pe));
    	return "index";
    }
     
    /**
     * ������Ϣ������
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("/queueSender")
    public String queueSender(@RequestParam("message")String message){
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