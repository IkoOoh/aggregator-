package com.boots.back;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.action.AdvertisementAction;
import com.boots.action.ChatActions;

import DTO.ChatDto;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

	private static final long serialVersionUID = 2170574342604670255L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		if(request.getRequestURI().equals(request.getContextPath()+"/Chat")) {
			try {
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				
				ChatDto cht = chta.getChat(customer, advId);
				
				
				
				if(cht.getOwnerName().equals(username) || cht.getCustomerUsername().equals(username) ) {
					request.setAttribute("chat", cht);
					
					
					AdvertisementAction action = new AdvertisementAction(request, response);
					ArrayList<Integer> ids =  action.getAdvertisementsId(username);
					chta.getChats(ids , "AdvChats");
					System.out.println(ids.size()+"ids size");
					chta.getChats(username , "CustomerChats");
					getServletContext().getRequestDispatcher("/WEB-INF/views/ChatPage.jsp").forward(request, response);
				}else {
					throw new NullPointerException();
				}
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect data");
				System.out.println("incorrect data");
			}
			catch(NumberFormatException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect adv");
				System.out.println("incorrect adv");
			}
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		if(request.getRequestURI().equals(request.getContextPath()+"/Chat")) {
			try {
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				chta.createChat(customer, advId);
				response.sendRedirect(request.getContextPath() + "/Chat?adv="+adv+"&customer="+customer);
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect data");
				System.out.println("incorrect data");
			}
			catch(NumberFormatException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect adv");
				System.out.println("incorrect adv");
			}
			return;
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		if(request.getRequestURI().equals(request.getContextPath()+"/Chat")) {
			try {
				System.out.println("deletion request");
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				chta.deleteChat(advId, customer, "/MainPage");
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect data");
				System.out.println("incorrect data");
			}
			catch(NumberFormatException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,"incorrect adv");
				System.out.println("incorrect adv");
			}
			return;
		}
	}
}
