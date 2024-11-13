<%@page import="com.boots.entity.Advertisement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<div class="ad-container">
 <% Advertisement adv = (Advertisement)request.getAttribute("ad"); %>
	<div class="ad-photo">
 		<img src="photo1.jpg" alt="Фото объявления 1">
	</div>
	 <div class="ad-details">
	 	<a href="<%= request.getContextPath() %>/Advertisement?ad=<%=adv.getId()%>" class="ad-title"><%=adv.getTitle()%></a>
 		<p class="ad-description"><%=adv.getDescription()%></p>
	     <div class="ad-contacts">
	        <p>Контакты:</p>
	        <p><%=adv.getContacts()%></p>
		</div>
	</div>
</div>

</html>