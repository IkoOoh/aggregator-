package com.boots.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.service.ChatService;

import DTO.ChatDto;

public class ValidationAction {
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public ValidationAction(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public Boolean Validate(int chatId) {
		String username = getUserName();
		if(username.isEmpty()) {
			return false;
		}
		ChatService chatService = new ChatService();
		ChatDto chatDto = chatService.getChatDto(chatId);
		if(chatDto==null) {
			return false;
		}
		return (username.equals(chatDto.getCustomerUsername())||username.equals(chatDto.getOwnerName())); 
	}
	
	private String getUserName() {
		try {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		return username;
		}catch(NullPointerException e){
				try {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect data");
				} catch (IOException e1) {
				}
			System.out.println("incorrect data");
			return "";
		}
	}
}
