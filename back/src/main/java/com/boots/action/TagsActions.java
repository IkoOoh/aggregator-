package com.boots.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boots.service.TagsService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TagsActions {
	private HttpServletRequest request;
		
	private HttpServletResponse response;
	
	public TagsActions(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public void postTags() throws IOException{
		TagsService tagsser = new TagsService();
		ObjectMapper objM = new ObjectMapper();
		String jsonResponse = objM.writeValueAsString(tagsser.getTags());
		response.getWriter().write(jsonResponse);
	}
}
