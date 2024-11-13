<%@page import="java.util.ArrayList"%>
<%@page import="com.boots.entity.Advertisement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>	 
<body>
		<%Object obj = request.getAttribute("showingAdv");
		System.out.println(obj==null);
		if(obj!=null){
			List<Advertisement> usersAd = (List<Advertisement>) obj; 
			Advertisement adv = usersAd.getFirst();
			ArrayList<String> tagList = adv.getTags();
			String lastTag;
			if(tagList.isEmpty()){
				lastTag = "";
			}else{
				lastTag = tagList.getLast();
			}
			request.setAttribute("currentTag", lastTag);
			%>
			  <div class = "TitleField">
			  	<textarea id="title" class = "TitleStyle"  placeholder="Введите Заголовок"><%=adv.getTitle() %></textarea >
	 		 </div>
	 		 <div class = "MediaField">

	 		 </div>
	 		 <div class = "TagsField">
	 		 	<jsp:include page="categories.jsp"/>
	 		 </div>
	 		  <div class = "DescriptionField">
	 		 	<h2 class = "Heading">Описание</h2>
			  	<textarea id="description" class = "DescriptionStyle" placeholder="Введите Описание"><%=adv.getDescription() %></textarea>
	 		 </div>
	 		  <div class = "ContactsField">
			  	<h2 class = "Heading">Контакты</h2>
			  	<textarea id="contacts" class = "DescriptionStyle" placeholder="Введите Контакты"><%=adv.getContacts() %></textarea>
	 		 </div>
			<%
		}%>
</body>	 
			 
</html>