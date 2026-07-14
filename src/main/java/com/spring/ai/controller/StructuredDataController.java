package com.spring.ai.controller;

import com.spring.ai.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class StructuredDataController {

    private final ChatClient chatClient;

    public StructuredDataController(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping(path = "/structured")
    public ResponseEntity<CountryCities> chatBean(@RequestParam("message") String message) {
        CountryCities cc = chatClient.prompt()
                .user(message)
                .call().entity(CountryCities.class);
        return ResponseEntity.ok(cc);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message) {
        List<String> cc = chatClient.prompt()
                .user(message)
                .call().entity(new ListOutputConverter());
        return ResponseEntity.ok(cc);
    }

    @GetMapping(path = "/map")
    public ResponseEntity<Map<String, Object>> chatMap(@RequestParam("message") String message) {
        Map<String, Object> cc = chatClient.prompt()
                .user(message)
                .call().entity(new MapOutputConverter());
        return ResponseEntity.ok(cc);
    }

    @GetMapping(path = "/bean")
    public ResponseEntity<CountryCities> chatBeanOutput(@RequestParam("message") String message) {
        CountryCities cc = chatClient.prompt()
                .user(message)
                .call().entity(new BeanOutputConverter<>(CountryCities.class));
        return ResponseEntity.ok(cc);
    }

    @GetMapping(path = "/bean-list")
    public ResponseEntity<List<CountryCities>> chatBeanOutputList(@RequestParam("message") String message) {
        List<CountryCities> cc = chatClient.prompt()
                .user(message)
                .call().entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });
        return ResponseEntity.ok(cc);
    }

}
