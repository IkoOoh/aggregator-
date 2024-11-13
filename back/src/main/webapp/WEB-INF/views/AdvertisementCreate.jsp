<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/adv-form.css">
 <link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/advertisementCreate.css">
</head>
<body>
	<jsp:include page = "adv-form.jsp"/>
		 <span class = "SaveField">
	 		 	<button class="SaveStyle" onclick="save()">Сохранить объявление</button>
	 		 	 <a href="<%= request.getContextPath() %>/MainPage" class="return-button">Вернуться на главную страницу</a> 
	 	<script>
	 	window.onload = function() {
	 	   start(enableElements);
		 };
	 	function save(){
			   const titleValue = document.getElementById('title').value;
		       const descriptionValue = document.getElementById('description').value;
		       const contactsValue = document.getElementById('contacts').value;

		       const data = {
		           title: titleValue,
		           description: descriptionValue,
		           contacts: contactsValue,
		       };

		       fetch('/back/Advertisement', {
		           method: 'POST',
		           headers: {
		               'Content-Type': 'application/json',
		           },
		           body: JSON.stringify(data)
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
</body>
<style>
 </style>
</html>