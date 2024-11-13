<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boots.entity.Advertisement" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Основная страница</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/main.css">
    <script type="text/javascript" src="<%= request.getContextPath() %>/res/scripts/dropFormDeleteJs.js"></script>
</head>
<body>
            <% 
                String username = (String) session.getAttribute("username");
			%>
			<jsp:include page="top-bar.jsp"/>
			<jsp:include page="SideChatList.jsp"/>
    		<%
        if (username != null) {
            %>
                 <div class="sidebar">
    				<h2>Ваши объявления</h2>
    				<div> 
    					<div class="userAdv">
    				<%
    				Object obj = request.getAttribute("usersAd");
    				if(obj!=null){
    					List<Advertisement> usersAd = (List<Advertisement>) obj;
    					 for (Advertisement ad : usersAd) { %>
    			        		<a href="<%= request.getContextPath() %>/Advertisement?ad=<%=ad.getId()%>" class="ad-owner-link"><%=ad.getTitle()%></a>
    			        <%}
    			        	} 
    	            request.removeAttribute("usersAd");%>
    	            	</div>
    	            <form method ="get" class = "ad-new" action = "<%= request.getContextPath() %>/Advertisement/create" >
    	            	 <button type="submit" class="ad-link">новое объявление</button>
    	            </form>
    				</div>
				</div>
	
            <% 
                }
            %>
    
      <div class="main-content">
		<form class="search-container" action="<%= request.getContextPath() %>/MainPage/Search" method="get">
		    <div class="search-row">
		        <input type="text" class="search-input" name="title" placeholder="Поиск..." value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>">
		        <button type="submit" class="search-button">Найти</button>
		    </div>
		    <jsp:include page="categories.jsp"/>
		    <script>
		    window.addEventListener('load',start(enableElements));
		 	</script>
		</form>

	    <div class="ad-list-container">
	    
		<% Object obj = request.getAttribute("adList");
		if(obj!=null){
			List<Advertisement> adList = (List<Advertisement>) obj;
        for (Advertisement ad : adList) {
       		 request.setAttribute("ad", ad); %>
        	<jsp:include page="Advertisement.jsp"/>
        <%}
        	} %>
        
	    </div>
	</div>

</body>
</html>