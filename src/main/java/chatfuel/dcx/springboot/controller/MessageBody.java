package com.websystique.springboot.controller;

import java.util.List;

public class MessageBody {

	private List<TextMessages> messages;

	public List<TextMessages> getMessages() {
		return messages;
	}

	public void setMessages(List<TextMessages> messages) {
		this.messages = messages;
	}
	
	public String toString(){
		return "this is a string";
	}
}
