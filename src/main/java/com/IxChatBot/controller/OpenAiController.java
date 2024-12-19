package com.IxChatBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.IxChatBot.Model.ChatCompletionRequest;
import com.IxChatBot.Model.ChatCompletionResponse;
import com.IxChatBot.ServiceImpl.GeneratePrompService;
import com.IxChatBot.ServiceImpl.SqlServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OpenAiController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GeneratePrompService promptService;
	
	@Autowired
	SqlServiceImpl sqlMap;

	@PostMapping("/hitOpenaiApi")
	public Map<String, String> getOpenAiResponse(@RequestBody Map<String, String> request) {
		String prompt = request.get("prompt");
		ChatCompletionResponse response = null;
		Map<String, String> responseMap = new HashMap<>();

		try {

			String dynamicPrompt = promptService.generatePrompt(prompt);

			System.out.println("dynamicPrompt -- > " + dynamicPrompt);

			if (dynamicPrompt.contains("I'm sorry")) {
				// Create the request for ChatGPT
				ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest("gpt-3.5-turbo", dynamicPrompt,
						"user");

				// Call the OpenAI API
				response = restTemplate.postForObject("https://api.openai.com/v1/chat/completions",
						chatCompletionRequest, ChatCompletionResponse.class);
			} else {

				responseMap.put("answer", dynamicPrompt);

				System.out.println("response === > " + responseMap.toString());

				return responseMap;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("answer", "Error occurred: " + e.getMessage());
			return errorResponse;
		}

		// Extract the answer and return as JSON
//        Map<String, String> responseMap = new HashMap<>();
		if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
			responseMap.put("answer", response.getChoices().get(0).getMassage().getContent());
		} else {
			responseMap.put("answer", "No response from OpenAI");
		}
		return responseMap;
	}

}
