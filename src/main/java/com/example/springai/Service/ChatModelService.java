package com.example.springai.Service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

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
}
