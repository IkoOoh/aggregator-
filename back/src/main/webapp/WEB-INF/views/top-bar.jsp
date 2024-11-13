<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.top-bar {
    background-color: #4CAF50;
    color: white;
    padding: 10px;
    text-align: right;
}

.account {
    display: inline-block;
    padding: 0 20px;
}

.button {
    display: inline-block;
    padding: 10px 15px;
    font-size: 16px;
    color: black;
    background-color: white;
    border: 1px solid black; 
    border-radius: 15px; 
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
}

.button:hover {
    background-color: #808080; 
    color: white;
}
.user-account {
    display: inline-block;
}

.account-details {
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    color: black;
    background-color: white;
    border: 1px solid black; 
    border-radius: 15px; 
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
}

.username {
    font-weight: bold;
}
</style>
<div class="top-bar">
	        <div class="account">
	            <% 
	                String username = (String) session.getAttribute("username");
	                if (username != null) {
	            %>
	                <div class="user-account">
	    				<span class="account-details">Аккаунт: <span class="username"><%= username %></span></span>
					</div>
	                <a href="<%= request.getContextPath() %>/Login/LogOut" class="button">Выйти</a>
	            <% 
	                } else { 
	            %>
	                <a href="<%= request.getContextPath() %>/Login" class="button">Вход</a>
	                <a href="<%= request.getContextPath() %>/Registration" class="button">Регистрация</a>
	            <% 
	                } 
	            %>
	        </div>
	    </div>
</html>