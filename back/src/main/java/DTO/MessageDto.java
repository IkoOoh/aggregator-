package DTO;

import java.io.Serializable;

public class MessageDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String messageText;
	private String authorUsername;
	private Boolean isYour;
	public MessageDto(){
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getAuthorUsername() {
		return authorUsername;
	}
	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}
	public Boolean getIsYour() {
		return isYour;
	}
	public void setIsYour(Boolean isYour) {
		this.isYour = isYour;
	}
}
