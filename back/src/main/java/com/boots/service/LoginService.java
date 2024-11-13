package com.boots.service;

import java.util.ArrayList;

import com.boots.entity.User;

public class LoginService {
	public static ArrayList<User> users = new ArrayList<>();
	
	public static void start() {
		User u = new User();
		u.setUsername("pavel");
		u.setPassword("123");
		users.add(u);
		System.out.println(u.getUsername()+" "+u.getPassword()+" gen");
	}
	
	public static Boolean checkForUser(User user) {
		System.out.println(user.getUsername()+" "+user.getPassword());
		for (User u : users) {
			System.out.println(u.getUsername()+" "+u.getPassword()+" db");
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				System.out.println("succ");
				return true;
			}
		}
		return false;
	}
	
}
