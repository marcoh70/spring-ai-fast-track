package com.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/multi-model")
public class MultiModelController {

    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    public MultiModelController(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
                                @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping(path = "/openai")
    public String openAiChat(@RequestParam String message) {
        return openAiChatClient.prompt(message).call().content();
    }

    @GetMapping(path = "/ollama")
    public String ollamaChat(@RequestParam String message) {
        return ollamaChatClient.prompt(message).call().content();
    }
}
