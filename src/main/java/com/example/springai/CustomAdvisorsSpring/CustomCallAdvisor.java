package com.example.springai.CustomAdvisorsSpring;


import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class CustomCallAdvisor implements CallAdvisor {


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        System.out.println("Before call");
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        System.out.println("After call");
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "CustomCallAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
