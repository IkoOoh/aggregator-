<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход на сайт</title>
    <link rel="stylesheet" href="/back/res/css/login.css">
</head>
<body>
    <div class="login-form">
        <h2>Вход на сайт</h2>
        <form action="/back/Login/LoginInTo" method="get">
            <input type="text" name="username" placeholder="Имя пользователя" required>
            <input type="password" name="password" placeholder="Пароль" required>
            <button type="submit">Войти</button>
        </form>
        <a href="<%= request.getContextPath() %>/MainPage" class="return-button">Вернуться на главную страницу</a>
    </div>
</body>