package com.boots.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boots.entity.User;
import com.boots.service.RegistrationService;

public class RegistrationActions {
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	 
	public String onSuccess = "/MainPage";
	
	public String onFail = "";
	
	public RegistrationActions(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public void register() throws IOException {
		String name =  (String)request.getParameter("username");
		String password =  (String)request.getParameter("password");
		
		User user = new User(name,password);
		if(RegistrationService.createUser(user)) {
			LoginActions loginActions = new LoginActions(request, response);
			loginActions.onFail = onFail;
			loginActions.onSuccess = onSuccess;
			loginActions.login();
		}else {
			response.sendRedirect(request.getContextPath() + onFail);
		}
	}
	
	public static Boolean register(String name , String password) {
		return RegistrationService.createUser(new User(name,password));
	}
	
}
