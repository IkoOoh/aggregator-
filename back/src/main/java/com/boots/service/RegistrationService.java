package com.boots.service;

import com.boots.entity.User;



public class RegistrationService {
	public static Boolean createUser(User user) {
		for (User u : LoginService.users) {
			if(u.getUsername().equals(user.getUsername()))
				return false;
		}
		LoginService.users.add(user);
		return true;
	}
}
