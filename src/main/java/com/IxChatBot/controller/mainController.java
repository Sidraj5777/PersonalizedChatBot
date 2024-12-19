package com.IxChatBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class mainController {

	@GetMapping("/chat")
	public String getChatPage() {
		return "chat"; // Return chat.html template
	}
}
