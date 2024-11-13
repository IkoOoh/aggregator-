package com.boots.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boots.action.RegistrationActions;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8587870029755985844L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
	        if (request.getRequestURI().equals(request.getContextPath() +"/Registration")) {
	        	getServletContext().getRequestDispatcher("/WEB-INF/views/RegistrationPage.jsp").forward(request, response);
	        }
	 	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
			if (request.getRequestURI().equals(request.getContextPath() +"/Registration")) {
				RegistrationActions reActions = new RegistrationActions(request, response);
				reActions.onSuccess = "/MainPage";
				reActions.onFail = "/Registration";
				reActions.register();
	        }
	 	}
}
