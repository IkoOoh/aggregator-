package com.boots.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.boots.action.AdvertisementAction;
import com.boots.action.ChatActions;
import com.boots.action.MessageActions;
import com.boots.service.MessageService;

import DTO.ChatDto;


@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
		if(request.getRequestURI().equals(request.getContextPath()+"/Message/load")) {
			try{
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				ChatDto cht = chta.getChat(customer, advId);
				int id = cht.getId();
				
				String offset = request.getParameter("offset");
				String limit = request.getParameter("limit");
				
				int offsetI = Integer.parseInt(offset);
				int limitI =  Integer.parseInt(limit);
				System.out.println(offsetI + " -offset");
				System.out.println(limitI+ " -limit");
				MessageActions msgA = new MessageActions(request, response);
				msgA.getMessages(offsetI, limitI, id);
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			return;
		}
		
		if(request.getRequestURI().equals(request.getContextPath()+"/Message/getNew")) {
			try{
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				ChatDto cht = chta.getChat(customer, advId);
				int id = cht.getId();
				MessageActions msgA = new MessageActions(request, response);
				msgA.getLastMessages(id, 6);
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			return;
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURI().equals(request.getContextPath()+"/Message/create")) {
			try(BufferedReader reader = request.getReader()){
				
				String adv = request.getParameter("adv");
				String customer =request.getParameter("customer");
				ChatActions chta = new ChatActions(request, response);
				int advId = Integer.parseInt(adv);
				ChatDto cht = chta.getChat(customer, advId);
				int id = cht.getId();
				
				MessageActions msgA = new MessageActions(request, response);
				StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line);
		        }
		        String requestBody = sb.toString();
				JSONParser parser = new JSONParser();
			    JSONObject jsonObject = (JSONObject)parser.parse(requestBody);
			    String messageText = (String)jsonObject.get("messageText");
			    
				msgA.createMessage(id, messageText);
			}catch(NullPointerException e){
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (ParseException e) {
				if(!response.isCommitted())
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			return;
			}
		}
}
