package com.riches.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riches.mq.consumer.dto.User;
import com.riches.mq.provider.QueueSender;

@Controller
public class ActivemqController {
     
    @Resource QueueSender queueSender;
    
    @RequestMapping("/")
	public String index(HttpServletRequest request) {
    	return "index";
    }
     
    /**
     * 发送消息到队列
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