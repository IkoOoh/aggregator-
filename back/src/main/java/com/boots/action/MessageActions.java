package com.boots.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.entity.Message;
import com.boots.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.MessageDto;

public class MessageActions {
private HttpServletRequest request;
	
private HttpServletResponse response;

public MessageActions(HttpServletRequest request, HttpServletResponse response){
	this.request = request;
	this.response = response;
}

public void getMessages(int offset,int limit, int chatId) throws IOException {
	ValidationAction va = new ValidationAction(request, response);
	if(!va.Validate(chatId)) {
		if(!response.isCommitted())
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"incorrect account");
		return;
	}
	HttpSession session = request.getSession(false);
	String username = (String) session.getAttribute("username");
	MessageService messagesService = new MessageService();
	ObjectMapper objM = new ObjectMapper();
	ArrayList<Message> messages = messagesService.getMessages(chatId, offset, limit);
	ArrayList<MessageDto> messagesDto = new ArrayList<>();
	for (Message message : messages) {
		MessageDto messageDto = new MessageDto();
		messageDto.setAuthorUsername(message.getAuthorUsername());
		messageDto.setMessageText(message.getMessageText());
		messageDto.setId(message.getId());
		messageDto.setIsYour(message.getAuthorUsername() == username );
		messagesDto.add(messageDto);
	}
	String jsonResponse = objM.writeValueAsString(messagesDto);
	response.getWriter().write(jsonResponse);
}

public void getLastMessages(int chatId,int time) throws IOException {
	ValidationAction va = new ValidationAction(request, response);
	if(!va.Validate(chatId)) {
		if(!response.isCommitted())
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"incorrect account");
		return;
	}
	HttpSession session = request.getSession(false);
	String username = (String) session.getAttribute("username");
	MessageService messagesService = new MessageService();
	ObjectMapper objM = new ObjectMapper();
	ArrayList<Message> messages = messagesService.getLastMessages(chatId, time);
	ArrayList<MessageDto> messagesDto = new ArrayList<>();
	for (Message message : messages) {
		MessageDto messageDto = new MessageDto();
		messageDto.setAuthorUsername(message.getAuthorUsername());
		messageDto.setMessageText(message.getMessageText());
		messageDto.setId(message.getId());
		messageDto.setIsYour(message.getAuthorUsername() == username );
		messagesDto.add(messageDto);
	}
	String jsonResponse = objM.writeValueAsString(messagesDto);
	response.getWriter().write(jsonResponse);
}

public void createMessage(int chatId,String text) throws IOException {
	ValidationAction va = new ValidationAction(request, response);
	if(!va.Validate(chatId)) {
		if(!response.isCommitted())
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"incorrect account");
		return;
	}
	HttpSession session = request.getSession(false);
	String username = (String) session.getAttribute("username");
	MessageService messagesService = new MessageService();
	ObjectMapper objM = new ObjectMapper();
	
	Message message = messagesService.createMessage(chatId, text, username);
	
	MessageDto messageDto = new MessageDto();
	messageDto.setAuthorUsername(message.getAuthorUsername());
	messageDto.setMessageText(message.getMessageText());
	messageDto.setId(message.getId());
	messageDto.setIsYour(message.getAuthorUsername() == username );
	String jsonResponse = objM.writeValueAsString(messageDto); 
	response.getWriter().write(jsonResponse);
}

}
