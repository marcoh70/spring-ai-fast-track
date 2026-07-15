package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

//    @Bean
//    public SimpleLoggerAdvisor loggerAdvisor() {
//        return new SimpleLoggerAdvisor();
//    }

    // Note: this properties as well can be configured in the application.yaml, in the case we want different
    // configurations for each environment, for example.
    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel){
        var options = OpenAiChatOptions.builder()
                .model("gpt-5.4-mini")
                .temperature(0.8);
                //.maxCompletionTokens(100);
        return ChatClient.builder(openAiChatModel)
                .defaultOptions(options)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .build();
    }

    /*
        We need to configure ollama instead adding properties in our application.yaml file when running multimodels apps
     */
    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel)
                .defaultOptions(
                        OllamaChatOptions.builder()
                                .model("llama3.2")
                                )
                .build();
    }
}
