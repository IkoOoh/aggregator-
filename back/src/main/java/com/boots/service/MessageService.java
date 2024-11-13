package com.boots.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.boots.entity.Message;

public class MessageService {
	public static ArrayList<Message> msgList = new ArrayList<>();
	public MessageService(){}
	
	public ArrayList<Message> getMessages(int chatId,int offset , int limit) {
		ArrayList<Message> retList = new ArrayList<>();
		msgList.sort((a,b)->{
			return -a.getSendDate().compareTo(b.getSendDate());
		});
		for (Message msg : msgList) {
			if(msg.getChatId()==chatId) {
				if(offset==0) {
					if(limit>0) {
						retList.add(msg);
						limit--;
					}else {
						break;
					}
				}else {
					offset--;
				}
			}
		}
		System.out.println(retList.size() + "amount of old messages");

		System.out.println(msgList.size() + "total amount of messages");
		return retList;
	}
	
	public ArrayList<Message> getLastMessages(int chatId,int time) {
		ArrayList<Message> retList = new ArrayList<>();
		msgList.sort((a,b)->{
			return -a.getSendDate().compareTo(b.getSendDate());
		});
		long currentTime = System.currentTimeMillis();
		for (Message msg : msgList) {
			long messageTime = msg.getSendDate().getTime();
			if(msg.getChatId()==chatId) {
				if( (messageTime-currentTime) <= (long)time*1000) {
					retList.add(msg);
				}
			}
		}
		System.out.println(retList.size() + "amount of last messages");

		System.out.println(msgList.size() + "total amount of messages");
		return retList;
	}
	
	public Message createMessage(int chatId , String message, String author) {
		LocalDateTime now = LocalDateTime.now();
	    Timestamp timestamp = Timestamp.valueOf(now);
		
		Message msg = new Message();
		msg.setAuthorUsername(author);
		msg.setChatId(chatId);
		msg.setMessageText(message);
		msg.setSendDate(timestamp);
		msg.setId(msgList.size()+1);
		System.out.println("message created");
		msgList.add(msg);
		return msg;
	}
	
	public ArrayList<Message> getMessages(int chatId) {
		ArrayList<Message> retList = new ArrayList<>();
		msgList.sort((a,b)->{
			return -a.getSendDate().compareTo(b.getSendDate());
		});
		for (Message msg : msgList) {
			if(msg.getChatId()==chatId) {
				retList.add(msg);
			}
		}
		System.out.println(retList.size() + "amount of last messages");

		System.out.println(msgList.size() + "total amount of messages");
		return retList;
	}
	public void removeMessages(int chatId) {
		msgList.sort((a,b)->{
			return -a.getSendDate().compareTo(b.getSendDate());
		});
		System.out.println(msgList.size() + "total amount of messages before remove");
		msgList = (ArrayList<Message>)msgList.stream().filter(a->{return a.getChatId()!=chatId;} ).toList();
		System.out.println(msgList.size() + "total amount of messages after remove");
	}
	
}
