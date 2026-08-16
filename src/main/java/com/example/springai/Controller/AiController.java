package com.example.springai.Controller;

import com.example.springai.Service.ChatModelService;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class AiController {

    ChatModelService chatModelService;

    public AiController(ChatModelService chatservice){
        chatModelService=chatservice;
    }

    @GetMapping("/chat")
    public void chatWithModel(@RequestParam String message){
        ChatResponse chatResponse= chatModelService.callChatModel(message);
//        chatResponse.getResult().getOutput().getText() -> for messsage ( message converted to prompt nd send to model)
//        System.out.println(chatResponse.getResult().getOutput().getText());
//        System.out.println(chatResponse);
        System.out.println(chatResponse.getMetadata().getModel());
        System.out.println(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/chat/v2")
    public void chatWithSystem(@RequestParam String sysmsg,@RequestParam String usrmsg){
        System.out.println(chatModelService.systemMessageModel(sysmsg,usrmsg).getResult().getOutput().getText());


    }
}
