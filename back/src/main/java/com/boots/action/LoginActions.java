package com.boots.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.entity.User;
import com.boots.service.LoginService;

public class LoginActions {
	HttpServletRequest request;
	
	HttpServletResponse response;
	 
	public String onSuccess = "/MainPage";
	
	public String onFail = "";
	public LoginActions(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public void login() throws IOException {
		String name = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		
		User user = new User();
		user.setUsername(name);
		user.setPassword(password);
		if(LoginService.checkForUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			response.sendRedirect(request.getContextPath() + onSuccess);
		}else {
			response.sendRedirect(request.getContextPath() + onFail);
		}
		
	}
}
