package com.boots.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boots.entity.Advertisement;
import com.boots.service.AdvertisementService;
import com.boots.service.ChatService;

public class AdvertisementAction {
	HttpServletRequest request;
	
	HttpServletResponse response;
	
	public String title;
	
	public String userName;
	
	public String description;
	
	public String contacts;
	
	public int count = 0;
	
	public ArrayList<String> tags;
	
	public String attributeName = "adList";
	
	public int id = 0;
	
	public AdvertisementAction(HttpServletRequest request, HttpServletResponse response){
		Advertisement a = new Advertisement();
		a.setUser_username("pavel");
		a.setTitle("PavelTitle");
		a.setId(AdvertisementService.adv.size() + 1);
		a.setTag("112");
		AdvertisementService.adv.add(a);
		this.request = request;
		this.response = response;
		tags = new ArrayList<>();
	}
	
	public ArrayList<Advertisement> showAdvertisements() throws ServletException, IOException {
		ArrayList<Advertisement> addList = AdvertisementService.getAdvertisements(count ,title, tags, userName);
		request.setAttribute(attributeName, addList);
		return addList;
	}
	
	public ArrayList<Integer> getAdvertisementsId(String username) throws ServletException, IOException {
		ArrayList<Advertisement> addList = AdvertisementService.getAdvertisements(0 ,"", new ArrayList<String>(), username);
		ArrayList<Integer> ids = new ArrayList<>();
		for (Advertisement adv : addList) {
			ids.add(adv.getId());
		}
		return ids;
	}
	
	
	public ArrayList<Advertisement> showById() throws ServletException, IOException {
		ArrayList<Advertisement> addList = AdvertisementService.getAdvertisements(id);
		request.setAttribute(attributeName, addList);
		return addList;
	}
	public Boolean deleteById() throws IOException {
		HttpSession session =  request.getSession(false);
		if(session!=null&& session.getAttribute("username")!=null){
			String username = (String)session.getAttribute("username");
			Boolean f =  AdvertisementService.deleteAdvertisement(id , username);
		if(!f) {
			response.sendError(401,"Wrong Session");
			return false;
		}
			return true;
		}else {
			response.sendError(401,"Wrong Session");
		}
		return false;
	}
	public int createNewAdvertisement() {
		Advertisement ad = new Advertisement();
		ad.setUser_username(userName);
		ad.setContacts(contacts);
		ad.setDescription(description);
		ad.setTitle(title);
		for (String t : tags) {
			ad.setTag(t);
		}
		return AdvertisementService.createAdvertisement(ad);
	}
	
	public Boolean changeAdvertisement() {
		Advertisement ad = new Advertisement();
		
		ad.setId(id);
		ad.setUser_username(userName);
		ad.setContacts(contacts);
		ad.setDescription(description);
		ad.setTitle(title);
		for (String t : tags) {
			ad.setTag(t);
		}

		return AdvertisementService.changeAdvertisement(ad);
	}
	
	public void showEmptyAdvertisement(){
		Advertisement ad = new Advertisement();
		ad.setId(-1);
		ad.setContacts("");
		ad.setDescription("");
		ad.setTitle("");
		ad.setUser_username("");
		ArrayList<Advertisement> addList = new ArrayList<>();
		addList.add(ad);
		request.setAttribute(attributeName, addList);
	}
}
