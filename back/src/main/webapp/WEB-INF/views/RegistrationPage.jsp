<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/registration.css">
</head>
<body>
    <div class="registration-form">
        <h2>Регистрация</h2>
        <form id="registrationForm" action="/back/Registration" method="post" onsubmit="return validatePasswords()">
            <input type="text" name="username" placeholder="Имя пользователя" required>
            <input type="password" id="password" name="password" placeholder="Пароль" required>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Подтвердите пароль" required>
            <button type="submit">Зарегистрироваться</button>
        </form>
        <a href="<%= request.getContextPath() %>/MainPage" class="return-button">Вернуться на главную страницу</a>
    </div>
    <script>
        function validatePasswords() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            
            if (password !== confirmPassword) {
                alert("Пароли не совпадают. Пожалуйста, попробуйте снова.");
                return false;  // Остановить отправку формы
            }
            return true;  // Разрешить отправку формы
        }
    </script>
</body>
</html>