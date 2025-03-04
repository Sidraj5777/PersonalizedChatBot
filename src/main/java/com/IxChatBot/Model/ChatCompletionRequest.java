package com.IxChatBot.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatCompletionRequest {
	
	private String model;
	private List<ChatMessage> messages;
	
	
	public ChatCompletionRequest(String model, String prompt , String oprator) {
		
		this.model = model;
		this.messages = new ArrayList<ChatMessage>();
		
		this.messages.add(new ChatMessage(oprator,prompt));
			
	}
	
}

