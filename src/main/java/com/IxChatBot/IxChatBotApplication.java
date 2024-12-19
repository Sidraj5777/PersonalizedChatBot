package com.IxChatBot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class IxChatBotApplication {

    @Value("${Openai.key}")  
    private String openApiKey;

	public static void main(String[] args) {
        SpringApplication.run(IxChatBotApplication.class, args);
       
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        
        // Adding an interceptor to set the OpenAI key in the header
        restTemplate.getInterceptors().add((request, body, execution) -> {
        	
            request.getHeaders().add("Authorization", "Bearer " + openApiKey);
            
            return execution.execute(request, body);
        });
        
        return restTemplate;
    }
}
