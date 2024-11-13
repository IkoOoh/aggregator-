<%@page import="com.boots.entity.Advertisement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%= request.getContextPath() %>/res/scripts/editAdv.js"></script>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/advertisement.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/adv-form.css">
<title>Просмотр объявления</title>
</head>
<body>
		<jsp:include page="top-bar.jsp"/>
		<% 
          String username = (String) session.getAttribute("username");
		%>
		
	    <div class = "Ad">
	    <span class = "ReturnContainer">
	    <a href="<%= request.getContextPath() %>/MainPage" class="return-button">Вернуться на главную страницу</a> 
	    <%Object obj = request.getAttribute("showingAdv");
			if(obj!=null){
				List<Advertisement> usersAd = (List<Advertisement>) obj; 
				Advertisement adv = usersAd.getFirst();
	    
	   		if (username != null && username.equals(adv.getUser_username())) { %>
	    <button id = "editB" class="edit-button" onclick = "edit()">Изменить объявление</button>
	    <button id = "saveB" class="editor-buttons" onclick = "save()">Сохранить</button>
	    <button id = "cancelB" class="editor-buttons" onclick = "cancel()">Отменить изменения</button>
	    <% 
	   	} 
	   		}%>
	    </span>
	    
		<%
		if(obj!=null){
			List<Advertisement> usersAd = (List<Advertisement>) obj; 
			Advertisement adv = usersAd.getFirst();
			%>
			<jsp:include page = "adv-form.jsp"/>
	 		 <%if (username != null && username.equals(adv.getUser_username())) { %>
	 		  <span class = "DeleteField">
	 		  
	 		 	<button class="DeleteStyle" onclick="sendDeleteRequest()">Удалить объявление</button>
	 		 	<script>
				    function sendDeleteRequest() {
				        fetch('/back/Advertisement?ad=<%=request.getParameter("ad")%>', {
				            method: 'DELETE',
				        }).then(response => {
				            if (response.redirected) {
				                window.location.href = response.url; 
				            } else {
				                return response.json();
				            }
				        }).catch((error) => {
				            console.error('Error:', error);
				        });
				    }
				</script>

	 		 </span>
	 		 <%}else if(username != null){%> 
	 		 <div class = "ChatField">
   				 <a href="#" class="ChatLinkStyle" onclick="sendPostRequest(); return false;">Написать продавцу</a>
	 		 </div>
			 <%} %>
			<%
		}%>
</div>
    <script>
        function sendPostRequest() {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = '/back/Chat';

            var advField = document.createElement('input');
            advField.type = 'hidden';
            advField.name = 'adv';
            advField.value = '<%=request.getParameter("ad")%>';
            form.appendChild(advField);

            var customerField = document.createElement('input');
            customerField.type = 'hidden';
            customerField.name = 'customer';
            customerField.value = '<%=username%>';
            form.appendChild(customerField);

            document.body.appendChild(form);
            form.submit();
        }
    </script>

</body>
</html>