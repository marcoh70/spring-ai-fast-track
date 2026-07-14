package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ai/memory")
public class ChatMemoryController {

    private final ChatClient chatClient;


    public ChatMemoryController(@Qualifier("chatClientMemory") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public ResponseEntity<String> chatMemory(@RequestHeader("userName") String userName,
                            @RequestParam("message") String message){
        return ResponseEntity.ok(chatClient.prompt()
                .user(message)
                        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userName))
                .call().content());
    }
}
