package com.spring.ai.controller;

import com.spring.ai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final OpenAiService openAiService;

    @GetMapping(path = "/openai")
    public String getMessage(@RequestParam String message) {
        return openAiService.chatOpenAi(message);
    }
}
