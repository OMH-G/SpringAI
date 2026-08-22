package com.example.springai.CustomAdvisorsSpring;


import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class TimeCallAdvisor implements CallAdvisor {


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        long start=System.currentTimeMillis();
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        System.out.println("Time lasted :"+ String.valueOf(System.currentTimeMillis()-start));
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
