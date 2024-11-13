package com.boots.back;

import java.io.BufferedReader;
import java.io.IOException;

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
import com.boots.entity.Advertisement;


@WebServlet(name = "AdvertisementServlet")
public class AdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = -14326399626745988L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        if(request.getRequestURI().equals(request.getContextPath()+"/Advertisement/create")) {
        	AdvertisementAction advertisement = new AdvertisementAction(request, response);
        	advertisement.attributeName = "showingAdv";
        	advertisement.showEmptyAdvertisement();
			getServletContext().getRequestDispatcher("/WEB-INF/views/AdvertisementCreate.jsp").forward(request, response);
			return;
		}
		try {
				String par = request.getParameter("ad");
				int id = Integer.parseInt(par);
				AdvertisementAction advertisement = new AdvertisementAction(request, response);
			  	advertisement.id = id;
				advertisement.attributeName = "showingAdv";
				advertisement.showById();
				getServletContext().getRequestDispatcher("/WEB-INF/views/AdvertisementPage.jsp").forward(request, response);				
		}catch(Exception e){
			response.sendError(404,"Advertisement with such adress didn't exists");
		}
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(requestBody);
			String title = (String) jsonObject.get("title");
			String description = (String) jsonObject.get("description");
			String contacts = (String) jsonObject.get("contacts");
			
			HttpSession session =  request.getSession(false);
			if(session!=null&& session.getAttribute("username")!=null){
				String username = (String)session.getAttribute("username");
				AdvertisementAction aa = new AdvertisementAction(request, response);
				aa.userName = username;
				aa.title = title;
				aa.description = description;
				aa.contacts = contacts;
				int id = aa.createNewAdvertisement();
				response.sendRedirect(request.getContextPath() + "/Advertisement?ad="+id);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(requestBody);
			String title = (String) jsonObject.get("title");
			String description = (String) jsonObject.get("description");
			String contacts = (String) jsonObject.get("contacts");
			String id = (String) jsonObject.get("id");
			
			HttpSession session =  request.getSession(false);
			if(session!=null&& session.getAttribute("username")!=null){
				String username = (String)session.getAttribute("username");
				AdvertisementAction aa = new AdvertisementAction(request, response);
				aa.userName = username;
				aa.title = title;
				aa.description = description;
				aa.contacts = contacts;
				aa.id = Integer.parseInt(id);
				aa.changeAdvertisement();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
		try {
			String par = request.getParameter("ad");
			int id = Integer.parseInt(par);
			AdvertisementAction advertisement = new AdvertisementAction(request, response);
			advertisement.id = id;
			advertisement.deleteById();
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/MainPage");
			}
		}catch(Exception e){
			
		}
	}
}
