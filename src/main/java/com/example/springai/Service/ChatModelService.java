package com.example.springai.Service;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ollama.autoconfigure.OllamaChatProperties;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatModelService {

    private ChatModel chatModel;
    public ChatModelService(ChatModel model){
        this.chatModel=model;
    }

    public ChatResponse callChatModel(String message){
        Prompt prompt= new Prompt(message);
        System.out.println(prompt.getUserMessage().getText());
        return chatModel.call( prompt);
    }

    public ChatResponse systemMessageModel(String sysmsg,String usrmsg){
        SystemMessage systemMessage= new SystemMessage(sysmsg);
        UserMessage userMessage= new UserMessage(usrmsg);
        System.out.println(systemMessage.getText());
        System.out.println(userMessage.getText());
        OllamaChatOptions options= OllamaChatOptions.builder().temperature(9.0).model("qwen2.5-coder:1.5b").build();
        Prompt prompt=new Prompt(List.of(systemMessage,userMessage),options);

        return chatModel.call(prompt);
    }
}