package com.boots.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Advertisement {
	private int id;
	private String user_username;
	private String title;
	private String description;
	private String contacts;
	
	private HashSet<String> tags;
	
	public Advertisement() {
		tags = new HashSet<String>();
	}
	public ArrayList<String> getTags() {
		ArrayList<String> rl = new ArrayList<>();
		for (String st : tags) {
			rl.add(st);
		}
		
		return rl;
	}
	
	public Boolean setTag(String tag) {
		return tags.add(tag);
	}
	
	public Boolean removeTag(String tag) {
		return tags.remove(tag);
	}
	
	public Boolean containsTag(String tag) {
		return tags.contains(tag);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_username() {
		return user_username;
	}
	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
}
