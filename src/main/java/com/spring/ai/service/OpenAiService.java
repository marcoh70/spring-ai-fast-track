package com.spring.ai.service;

import com.spring.ai.config.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final ChatClient chatClient;

    public OpenAiService(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatOpenAi(String message) {
        return chatClient.prompt(message)
                .system("""
                        You are an internal HR assistant. Your role is to help\s
                        employess with questions related to HR policies, such as\s
                        leave policies, working hours, benefits, and code of conduct.
                        If a user asks for help with anything outside of these topics,\s
                        kindly inform them that you can only assist with queries related to HR policies.
                        """)
                .call().content();
    }
}
