package com.boots.service;

import java.util.ArrayList;

import tags.Tags;

public class TagsService {

	public static ArrayList<Tags> tagsList = new ArrayList<>();
	static {
		Tags tag = new Tags();
		tag.categoryName = "first";
		tag.parentName = null;
		ArrayList<String> strings = new ArrayList<>();
		strings.add("11");
		strings.add("12");
		strings.add("13");

		tag.childCategories = strings;
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "11";
		tag.parentName = "first";
		strings = new ArrayList<>();
		strings.add("111");
		strings.add("112");
		strings.add("113");
		tag.childCategories = strings;
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "12";
		tag.parentName = "first";
		strings = new ArrayList<>();
		strings.add("121");
		strings.add("122");
		strings.add("123");
		tag.childCategories = strings;
		tagsList.add(tag);		

		tag = new Tags();
		tag.categoryName = "13";
		tag.parentName = "first";
		strings = new ArrayList<>();
		strings.add("131");
		strings.add("132");
		strings.add("133");
		tag.childCategories = strings;
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "131";
		tag.parentName = "13";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "132";
		tag.parentName = "13";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "133";
		tag.parentName = "13";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		/////////////////////
		tag = new Tags();
		tag.categoryName = "121";
		tag.parentName = "12";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "122";
		tag.parentName = "12";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "123";
		tag.parentName = "12";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		/////////////////////
		tag = new Tags();
		tag.categoryName = "111";
		tag.parentName = "11";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "112";
		tag.parentName = "11";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
		
		tag = new Tags();
		tag.categoryName = "113";
		tag.parentName = "11";
		tag.childCategories = new ArrayList<>();
		tagsList.add(tag);
	}
	public TagsService(){};
	
	public ArrayList<Tags> getTags(){
		return tagsList;
	}
}
