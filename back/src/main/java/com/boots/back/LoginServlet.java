package com.boots.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.action.LoginActions;
import com.boots.entity.User;
import com.boots.service.LoginService;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 679471361818052103L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
		if(request.getRequestURI().equals(request.getContextPath() +"/Login")) {
			LoginService.start();
			getServletContext().getRequestDispatcher("/WEB-INF/views/LoginPage.jsp").forward(request, response);
			return;
		}
		
		if(request.getRequestURI().equals(request.getContextPath() +"/Login/LoginInTo")) {
			LoginActions loginActions = new LoginActions(request, response);
			loginActions.onFail = "/Login";
			loginActions.login();
			return;	
		}
		
		if(request.getRequestURI().equals(request.getContextPath() +"/Login/LogOut")) {
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() +"/MainPage");
			return;
		}
		
	 }
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 	}
}
