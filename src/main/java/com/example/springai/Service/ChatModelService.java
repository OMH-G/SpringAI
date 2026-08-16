package com.example.springai.Service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatModelService {

    private ChatModel chatModel;
    public ChatModelService(ChatModel model){
        this.chatModel=model;
    }

    public String  callChatModel(String message){
        return chatModel.call(message);
    }
}
