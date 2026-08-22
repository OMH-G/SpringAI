package com.example.springai.Controller;

import com.example.springai.Entity.JsonDto;
import com.example.springai.Service.ChatClientService;
import com.example.springai.Service.ChatModelService;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tools.jackson.databind.ObjectMapper;

import java.awt.*;
import java.util.Map;

@RestController
public class AiController {

    ChatModelService chatModelService;

    ChatClientService chatClientService;

    @Autowired
    ObjectMapper objectMapper;

    public AiController(ChatModelService chatservice,ChatClientService chatClientService){
        chatModelService=chatservice;
        this.chatClientService=chatClientService;
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

    @GetMapping("/chat/v3")
    public void chatWithClient(@RequestParam String usrmsg){
        System.out.println(chatClientService.callModelWithClient(usrmsg));
    }

    @GetMapping("/chat/v4")
    public void chatWithClientMapper(@RequestParam String usrmsg){
        JsonDto jsonDto=chatClientService.callModelWithClientMapper(usrmsg);
        System.out.println(objectMapper.writeValueAsString(jsonDto));

    }
    @GetMapping("/chat/v5")
    public void chatWithClientMapperRemovedSystem(@RequestParam String usrmsg){
        JsonDto jsonDto=chatClientService.callModelWithClientMapperRemovedSystem(usrmsg);
        System.out.println(objectMapper.writeValueAsString(jsonDto));

    }
    @GetMapping("/chat/v6")
    public Flux<String> chatWithClientMapperRemovedSystemFlux(@RequestParam String usrmsg){
        return chatClientService.callModelWithClientMapperRemovedSystemFlux(usrmsg);


    }
}
