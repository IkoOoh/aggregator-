package com.boots.back;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.action.AdvertisementAction;
import com.boots.action.ChatActions;
import com.boots.entity.Advertisement;


@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 6319262067708492467L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		if(request.getRequestURI().equals(request.getContextPath()+"/MainPage")) {
			try {
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				if(username != null) {
					AdvertisementAction advertisement = new AdvertisementAction(request, response);
	     			advertisement.userName = username;
	     			advertisement.attributeName = "usersAd";
	     			advertisement.showAdvertisements();
					
					AdvertisementAction action = new AdvertisementAction(request, response);
					ArrayList<Integer> ids =  action.getAdvertisementsId(username);
					
					ChatActions chta = new ChatActions(request, response);
					chta.getChats(ids , "AdvChats");
					System.out.println(ids.size()+"ids size");
					chta.getChats(username , "CustomerChats");
				}
				
			}catch(NullPointerException e){
				System.out.println(e.getMessage());
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/MainPage.jsp").forward(request, response);
			return;
		}
		if(request.getRequestURI().equals(request.getContextPath()+"/MainPage/Search")) {
			String title = request.getParameter("title");

	        String[] tagArray = request.getParameterValues("tags");
	        ArrayList<String> tags = new ArrayList<>();

	        if (tagArray != null) {
	            for (String tag : tagArray) {
	                if (tag != null && !tag.isEmpty()) {
	                	tags.add(tag);
	                }
	            }
	        }
			
	        AdvertisementAction advertisement = new AdvertisementAction(request, response);
			advertisement.title = title;
			advertisement.count = 10;
			advertisement.tags = tags;
			advertisement.showAdvertisements();
			
			getServletContext().getRequestDispatcher("/MainPage").forward(request, response);
			return;
		}
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
