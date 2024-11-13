package com.boots.service;

import java.util.ArrayList;

import com.boots.entity.Advertisement;
import com.boots.entity.Chat;
import com.boots.entity.Message;

import DTO.ChatDto;

public class ChatService {
	public static ArrayList<Chat> chats = new ArrayList<>();
	
	public ChatService(){
	}
	
	public Chat getChat(int advId,String username){
		for (Chat chat : chats) {
			if(chat.getCustomerUsername().equals(username)&&chat.getAdvId()==advId){
				return chat;
			}
		}
		return null;
	}
	
	
	public ArrayList<Chat> getChatList(int advId){
		ArrayList<Chat> retList = new ArrayList<>();
		for (Chat chat : chats) {
			if(chat.getAdvId()==advId){
				retList.add(chat);
			}
		}
		return retList;
	}
	
	public ArrayList<Chat> getChatList(String username){
		ArrayList<Chat> retList = new ArrayList<>();
		for (Chat chat : chats) {
			if(chat.getCustomerUsername().equals(username)){
				retList.add(chat);
			}
		}
		return retList;
	}
	
	
	public Chat getChat(int chatId){
		for (Chat chat : chats) {
			if(chat.getId()==chatId){
				return chat;
			}
		}
		return null;
	}
	
	public ChatDto getChatDto(int chatId){
		for (Chat chat : chats) {
			if(chat.getId()==chatId){
				ChatDto ret = new ChatDto();
				AdvertisementService advService = new AdvertisementService();
				Advertisement a =  advService.getAdvertisements(chat.getAdvId()).getFirst();
				ret.setOwnerName(a.getUser_username()); ;
				ret.setAdvName(a.getTitle());
				ret.setCustomerUsername(chat.getCustomerUsername());
				ret.setAdvId(chat.getAdvId());
				return ret;
			}
		}
		
		
		return null;
	}
	
	
	public Chat createChat(int advId,String username) {
		Chat c = new Chat();
		c.setAdvId(advId);
		c.setCustomerUsername(username);
		c.setId(chats.size()+1);
		chats.add(c);
		return c;
	}
	
	public Boolean deleteChat(int advId,String username){
		for (int i = 0;i<chats.size();i++){
			if(chats.get(i).getCustomerUsername().equals(username)&&chats.get(i).getAdvId()==advId){
				chats.remove(i);
				return true;
			}
		}
		return false;
	}
	public Boolean deleteChat(int chatId){
		for (int i = 0;i<chats.size();i++){
			if(chats.get(i).getId()==chatId){
				chats.remove(i);
				return true;
			}
		}
		return false;
	}

}
