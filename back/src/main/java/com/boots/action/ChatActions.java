package com.boots.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boots.entity.Advertisement;
import com.boots.entity.Chat;
import com.boots.service.AdvertisementService;
import com.boots.service.ChatService;
import com.boots.service.MessageService;

import DTO.ChatDto;

public class ChatActions {
	HttpServletRequest request;
	
	HttpServletResponse response;
	
	public ChatActions(HttpServletRequest request,	HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public Chat createChat(String customerUserName,int advId) {
		ChatService chts = new ChatService();
		try {
		Chat chat = chts.createChat(advId, customerUserName);
		return chat;
		}catch(Exception e) {
			System.out.println("exception in chat creation");
			return null;
		}
	}
	
	public ChatDto getChat(String customerUserName,int advId) {
		ChatService chts = new ChatService();
		try {
		Chat chat = chts.getChat(advId, customerUserName);
		ChatDto ret = new ChatDto();
		ret.setAdvId(chat.getAdvId());
		ret.setCustomerUsername(chat.getCustomerUsername());
		Advertisement a = AdvertisementService.getAdvertisements(advId).getFirst();
		ret.setAdvName(a.getTitle());
		ret.setOwnerName(a.getUser_username());
		ret.setId(chat.getId());
		return ret;
		}catch(Exception e) {
			System.out.println("exception in chats request");
			return null;
		}
	}
	
	public ArrayList<ChatDto> getChats(String customerUserName,String Attribute){
		try {
			ChatService cts = new ChatService();
			ArrayList<ChatDto> chats = new ArrayList<>();
			ArrayList<Chat> tempChatList = cts.getChatList(customerUserName);
			for (Chat ch : tempChatList) {
				ChatDto tempDto = new ChatDto();
				tempDto.setAdvId(ch.getAdvId());
				tempDto.setCustomerUsername(ch.getCustomerUsername());
				
				Advertisement a = AdvertisementService.getAdvertisements(ch.getAdvId()).getFirst();
				
				tempDto.setAdvName(a.getTitle());
				tempDto.setOwnerName(a.getUser_username());
				tempDto.setId(ch.getId());
				chats.add(tempDto);
			}
			request.setAttribute(Attribute, chats);
			System.out.println("chats were recieved");
			return chats;
		}catch(Exception e) {
			System.out.println("exception in chats request");
			return null;
		}
		
	}
	
	public ArrayList<ChatDto> getChats(ArrayList<Integer> advIds,String Attribute){
		try {
			ChatService cts = new ChatService();
			ArrayList<ChatDto> chats = new ArrayList<>();
			for (Integer i : advIds) {
				ArrayList<Chat> tempChatList = cts.getChatList(i);
				for (Chat ch : tempChatList) {
					ChatDto tempDto = new ChatDto();
					tempDto.setAdvId(ch.getAdvId());
					tempDto.setCustomerUsername(ch.getCustomerUsername());
					tempDto.setId(ch.getId());
					
					Advertisement a = AdvertisementService.getAdvertisements(ch.getAdvId()).getFirst();
					
					tempDto.setAdvName(a.getTitle());
					tempDto.setOwnerName(a.getUser_username());
					chats.add(tempDto);
				}
			}
			request.setAttribute(Attribute, chats);
			System.out.println("chats were recieved");
			return chats;
		}catch(Exception e) {
			System.out.println("exception in chats request");
			return null;
		}
	}
	
	public Boolean deleteChat(int chatId, String redirection){
		try {
		ChatService cts = new ChatService();
		MessageService msgS = new MessageService();
		msgS.removeMessages(chatId);
		response.sendRedirect(request.getContextPath() + redirection);
		return cts.deleteChat(chatId);
		}catch(Exception e) {
			System.out.println("exception in delete request");
			return null;
		}
	}
	
	public Boolean deleteChat(int advId, String customerUserName, String redirection){
		try {
		System.out.println("deletion Service");
		ChatService cts = new ChatService();
		response.sendRedirect(request.getContextPath() + redirection);
		return cts.deleteChat(advId,customerUserName);
		}catch(Exception e) {
			System.out.println("exception in delete request");
			return null;
		}
	}
	
}
