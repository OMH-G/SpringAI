package com.example.springai.Controller;

import com.example.springai.Service.ChatModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    ChatModelService chatModelService;

    public AiController(ChatModelService chatservice){
        chatModelService=chatservice;
    }

    @GetMapping("/chat")
    public void chatWithModel(@RequestParam String message){
        System.out.println(chatModelService.callChatModel(message));
    }
}
