<%@page import="java.util.List"%>
<%@page import="DTO.ChatDto"%>
<%@page import="com.boots.entity.Chat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.chatContainer {
    width: 12%;
    height: 80%;
    background-color: #4CAF50;
    color: white;
    padding: 20px;
    box-sizing: border-box;
    position: fixed;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    left: 0;
    top: 11%;
}

.chat-owner-link {
	margin-left: -23px;
    display: block; 
    margin-bottom: 5px;
    width: 90%; 
    padding: 10px 20px;
    font-size: 16px;
    color: black;
    background-color: white;
    border: 2px solid black;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
}


	.chat-owner-link:hover {
	    background-color: #f0f0f0; 
	    color: black;
}
.Title {
    margin-top: 0;
    font-size: 24px;
}
.Text {
    margin-top: 0;
    font-style: italic;
    color: gray;
    font-size: 16px;
}
</style>
<meta charset="UTF-8">
</head>
<body>
 	<% 
	     String username = (String) session.getAttribute("username");
	     if (username != null) {
	 %>
	<div class="chatContainer">
		<h2 class="Title">Чаты с покупателями</h2>
		<% 
		   Object obj =  request.getAttribute("AdvChats");
	       if(obj instanceof List<?>){
			   System.out.println("sidebar initialized");
	    	   if( !((ArrayList<?>)obj).isEmpty() && ((ArrayList<?>)obj).getFirst() instanceof ChatDto){
	    		   ArrayList<ChatDto> chatArray = (ArrayList<ChatDto>)obj;
	    		   for(ChatDto c : chatArray){
	    			%>
	    				<a href="<%= request.getContextPath() %>/Chat?adv=<%=c.getAdvId()%>&customer=<%=c.getCustomerUsername()%>" class="chat-owner-link">
	    				Объявление:<%= c.getAdvName().substring(0, 5) + "..."%> пользователь:<%=c.getCustomerUsername()%>
	    				</a>
	    			<% 
	    		   }
	    	   }else if( ((ArrayList<?>)obj).isEmpty() ){ 
	    		   %>
	    		      <h2 class="Text">Здесь пока ничего нет</h2>
	    		   <%
	    	   }
	       }
		%>
		<h2 class="Title">Чаты с продавцами</h2>
		<% 
		   obj =  request.getAttribute("CustomerChats");
	       if(obj instanceof ArrayList<?>){
	    	   if( !((ArrayList<?>)obj).isEmpty() && ((ArrayList<?>)obj).getFirst() instanceof ChatDto){
	    		   ArrayList<ChatDto> chatArray = (ArrayList<ChatDto>)obj;
	    		   for(ChatDto c : chatArray){
	    			%>
	    				<a href="<%= request.getContextPath() %>/Advertisement?ad=<%=c.getAdvId()%>" class="chat-owner-link">
	    				Объявление:<%= c.getAdvName().substring(0, 5) + "..."%> пользователь:<%=c.getCustomerUsername()%>
	    				</a>
	    			<% 
	    				}
	    		   }else if( ((ArrayList<?>)obj).isEmpty() ){ 
		    		   %>
		    		      <h2 class="Text">Здесь пока ничего нет</h2>
		    		   <%
		    	   }
	    	   }
	       }
		%>
	</div>
</body>
</html>