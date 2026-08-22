package com.example.springai.Service;

import com.example.springai.CustomAdvisorsSpring.CustomCallAdvisor;
import com.example.springai.CustomAdvisorsSpring.TimeCallAdvisor;
import com.example.springai.Entity.JsonDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ChatClientService {

    ChatClient chatClient;

    public ChatClientService(ChatClient client){
        chatClient=client;
    }

    public String callModelWithClient(String message){
        System.out.println("callModelWithClient");
        return chatClient.prompt().user(message).call().content();
    }
    public JsonDto callModelWithClientMapper(String message){
        System.out.println("callModelWithClientMapper");
        return chatClient.prompt().system("Provided {entity} to model such that map corresponding fields with appropriate values and be more specific to question which is asked").user(message).call().entity(JsonDto.class);
    }
    public JsonDto callModelWithClientMapperRemovedSystem(String message){
        System.out.println("callModelWithClientMapperRemovedSystem");
        return chatClient.prompt().advisors(new CustomCallAdvisor()).advisors(new TimeCallAdvisor()).system("You are an expert Java and Spring Boot developer.").user(message).call().entity(JsonDto.class);
    }
    public Flux<String> callModelWithClientMapperRemovedSystemFlux(String message){
        System.out.println("callModelWithClientMapperRemovedSystem");
        return chatClient.prompt().system("You are an expert Java and Spring Boot developer.").user(message).stream().content().doOnNext(chunk->{
            System.out.println("Received :"+chunk
            );
        });
    }
}
