<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<div id="imageContainer"></div>
<form id="uploadForm" enctype="multipart/form-data">
    <input type="file" id="fileInput" name="file">
    <button type="button" onclick="uploadOnPage()">Загрузить</button>
</form>

<script>

	let formData;
    function uploadOnPage() {
        const form = document.getElementById('uploadForm');
        formData = new FormData(form);
    }
    
    function uploadOnServer(){
        fetch('your-server-endpoint', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
    
    function hideImageUpload(){
    	 const form = document.getElementById('uploadForm');
         form.style.display = 'none';
    }
    
    function showImageUpload(){
   		const form = document.getElementById('uploadForm');
        form.style.display = 'inline';
    }
    
    function initializeImage(action){
    	fetch('your-server-endpoint', {
            method: 'GET',
            body: formData
        })
        .then(response => response.blob())
        .then(data => {
        	const imageUrl = URL.createObjectURL(blob);
        	const imgElement = document.createElement('img');
        	imgElement.src = imageUrl;
        	imgElement.style.maxWidth = '200px';
        	imgElement.style.maxHeight = '200px';
        	document.getElementById('imageContainer').appendChild(imgElement);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    	
		action();
    }
    
</script>

</body>
</html>