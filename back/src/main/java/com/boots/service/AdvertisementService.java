package com.boots.service;

import java.util.ArrayList;

import com.boots.entity.Advertisement;

public class AdvertisementService {
	
	public static ArrayList<Advertisement> adv = new ArrayList<Advertisement>();
	
	public static ArrayList<Advertisement> getAdvertisements(int maxCount , String title, ArrayList<String> tags, String userName){
		 ArrayList<Advertisement> retList = new ArrayList<>();
		for (Advertisement a : adv) {
			for (String t : tags) {
				System.out.println(t + " send");
			}
			if(userName!= null &&!userName.isEmpty()){
				if(!a.getUser_username().equals(userName)) {
					System.out.println("na");
					continue;
				}
			}
			if(title != null && !title.isEmpty()){
				if(!a.getTitle().equals(title)) {
					System.out.println("ti");
					continue;
				}
			}
			
			if(tags != null){
				Boolean f = true;
				for (String t : tags) {
					if(!a.containsTag(t)) {
						f = false;
						break;
					}
				}
				if(!f) {
				System.out.println("tg");
				continue;
				}
				
			}
			retList.add(a);
		}
		return retList;
	}
	public static ArrayList<Advertisement> getAdvertisements(int id){
		ArrayList<Advertisement> retList = new ArrayList<>();
		for (Advertisement a : adv) {
			if(a.getId() == id) {
				retList.add(a);
			}
		}
		return retList;
	}
	public static Boolean deleteAdvertisement(int id, String userName){
		for (int i = 0; i < adv.size(); i++) {
		    if (adv.get(i).getId()==id) {
		    	if(adv.get(i).getUser_username().equals(userName)) {
		    		 adv.remove(i);
		    		 return true;
		    	}
		    	return false;
		    }
		}
		return false;
	}
	
	public static int createAdvertisement(Advertisement ad){
		Advertisement a = new Advertisement();
		a.setUser_username(ad.getUser_username());
		a.setDescription(ad.getDescription());
		a.setContacts(ad.getContacts());
		a.setTitle(ad.getTitle());
		a.setId(AdvertisementService.adv.size() + 1);
		adv.add(a);
		return a.getId();
	}
	
	public static Boolean changeAdvertisement(Advertisement ad) {
		for (int i = 0; i < adv.size(); i++) {
			if(adv.get(i).getId() == ad.getId() 
					&& ad.getUser_username().equals(adv.get(i).getUser_username())) {
				
				adv.get(i).setDescription(ad.getDescription());
				adv.get(i).setContacts(ad.getContacts());
				adv.get(i).setTitle(ad.getTitle());
				return true;
			}
		}
		return false;
	}
}
	
