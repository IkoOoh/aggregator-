<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Чат</title>
    <style>
    	.body{
    	overflow-y: visible;
    	height: 100vh; 
    	margin: 0;
    	}
        .container {
		    padding: 20px;
        }
	    .top-bar {
		    background-color: #4CAF50;
		    color: white;
		    padding: 10px;
		    text-align: right;
		}
        .chatWindow {
		    width: 70%;
		    background-color: #4CAF50;
		    padding: 20px;
		    border-radius: 15px;
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    margin: 20px auto;
		    transition: all 0.5s ease;
		    height: 40%;
        }
        .CHbutton {
		    display: inline-block;
		    padding: 10px 15px;
		    margin: 20px;
		    font-size: 16px;
		    color: black;
		    background-color: white;
		    border: 1px solid black; 
		    border-radius: 15px; 
		    text-decoration: none;
		    transition: background-color 0.3s, color 0.3s;
		}
		.CHbutton:hover {
		    background-color: #808080; 
		    color: white;
		}
		.chat-container {
		  	display: flex;
			overflow-y: scroll;
            width: 90%;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            background-color: white;
            height: 350px;
            flex-direction: column-reverse;
        }

        .chat-input {
            width: 50%;
            margin: 0 auto;
            display: flex;
            margin-top: 10px;
        }

        .chat-input input[type="text"] {
            flex: 1;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
		.message {
            padding: 10px;
            margin: 10px 0;
            border-radius: 10px;
            max-width: 70%;
        }
        .message.user {
            background-color: #d1e7dd;
            text-align: right;
            margin-left: auto;
        }
        .message.other {
            background-color: #f8d7da;
            text-align: left;
            margin-right: auto;
        }

    </style>
</head>
<body>
  		<jsp:include page="top-bar.jsp"/>
    	<jsp:include page="SideChatList.jsp"/>
    <div class="container">
        <div class="chatWindow">
        <span>
        <a href="<%= request.getContextPath() %>/MainPage" class="CHbutton">Вернуться на главную страницу</a> 
        <button class="CHbutton" onclick="sendDeleteRequest()">Удалить чат</button>
        </span>
	 		 	<script>
				    function sendDeleteRequest() {
				    	 url = new URL('<%= request.getContextPath() %>/Chat',window.location.origin);
						 url.searchParams.append('adv', adv);
						 url.searchParams.append('customer', customer);
				    	
				    	
				        fetch(url, {
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
            <div class="chat-container" id="chatContainer">

		    </div>
		    <div class="chat-input">
		        <input type="text" id="messageInput" placeholder="Введите сообщение...">
		        <button class="CHbutton" onclick="sendMessage()">Отправить</button>
		    </div>
        </div>
    </div>
    <script>
    var urlParams = new URLSearchParams(window.location.search);
    var customer = urlParams.get('customer');
    var adv = urlParams.get('adv');
    var chatWindow = document.getElementById('chatContainer');
    var offset = 0;
    var existingMessageIds = new Set();
    const limit = 10;
    loadPreviousMessages();
    function sendMessage() {
        var messageInput = document.getElementById('messageInput');
        var messageText = messageInput.value;
        url = new URL('<%= request.getContextPath() %>/Message/create',window.location.origin);
		 url.searchParams.append('adv', adv);
		 url.searchParams.append('customer', customer);
		 
        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ messageText: messageText })
        })
        .then(response => response.json())
        .then(data => {
            prependMessage(data.id, data.authorUsername, data.messageText, true);
            messageInput.value = ''; 
        })
        .catch(error => console.error('Ошибка:', error));
 	}
    
    function prependMessage(id, messageText, authorUsername, isYour) {
        var chatWindow = document.getElementById('chatContainer');
        if (!existingMessageIds.has(id)) {
            var messageDiv = document.createElement('div');
            messageDiv.className = 'message ' + (isYour ? 'user' : 'other');
            messageDiv.textContent =  authorUsername + ': ' + messageText;
            chatWindow.insertBefore(messageDiv, chatWindow.firstChild);
            existingMessageIds.add(id);
        }
    }



	 function loadPreviousMessages() {
		 url = new URL('<%= request.getContextPath() %>/Message/load',window.location.origin);
		 url.searchParams.append('offset', offset);
		 url.searchParams.append('limit', limit);
		 url.searchParams.append('adv', adv);
		 url.searchParams.append('customer', customer);
	     fetch(url)
	         .then(response => response.json())
	         .then(messages => {
	             messages.reverse().forEach(message => {
	                 if (!existingMessageIds.has(message.id)) {
	                     var messageDiv = document.createElement('div');
	                     messageDiv.className = 'message ' + (message.isYour ? 'user' : 'other');
	                     messageDiv.textContent = message.authorUsername + ': ' + message.messageText;
	                     chatWindow.appendChild(messageDiv);
	                     existingMessageIds.add(message.id);
	                 }
	             });
	             offset += messages.length;
	         });
	 }

 chatWindow.addEventListener('scroll', function() {
     if (chatWindow.scrollTop === 0) {
         var offset = chatWindow.children.length;
         loadPreviousMessages();
     }
 });

 setInterval(function() { 

     url = new URL('<%= request.getContextPath() %>/Message/getNew',window.location.origin);
	 url.searchParams.append('adv', adv);
	 url.searchParams.append('customer', customer);
     
     fetch(url)
         .then(response => response.json())
         .then(messages => {
             messages.forEach(message => {
                 prependMessage(message.id, message.authorUsername, message.messageText, message.isYour);
             });
             offset += messages.length;
         });
 }, 5000); 

    </script>
</body>
</html>
