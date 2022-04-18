package com.commplc.API;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatAPI {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/publicChatRoom")
    public void greeting() throws Exception {
        HashMap<String, Integer> dataPlc = new HashMap<>();
        dataPlc.put("D100", 100);
        dataPlc.put("D101", 101);
        dataPlc.put("D102", 102);
        dataPlc.put("D103", 103);
        template.convertAndSend("/topic/publicChatRoom", dataPlc);
    }

    
}
