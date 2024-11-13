const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('ad');
var str = "";
var str1 = "";
var str2 = "";
window.onload = function() {
       document.getElementById("title").readOnly = true;
       document.getElementById("description").readOnly = true;
       document.getElementById("contacts").readOnly = true;
	   start(disableElements);
};

function edit() {
	var textarea = document.getElementById('title');
    var textarea1 = document.getElementById('description');
	var textarea2 = document.getElementById('contacts');
	str = textarea.value;
	str1 = textarea1.value;
	str2 = textarea2.value;
	showEditMode();
}

function showEditMode(){
	document.getElementById('editB').style.display = 'none';
	document.getElementById('saveB').style.display = 'inline';
	document.getElementById('cancelB').style.display = 'inline';	
	var textarea = document.getElementById('title');
	var textarea1 = document.getElementById('description');
	var textarea2 = document.getElementById('contacts');
	textarea.readOnly = false;
	textarea1.readOnly = false;
	textarea2.readOnly = false;
	enableElements()
}

function showObsMode(){
	document.getElementById('editB').style.display = 'inline';
	document.getElementById('saveB').style.display = 'none';
	document.getElementById('cancelB').style.display = 'none';	
	var textarea = document.getElementById('title');
	var textarea1 = document.getElementById('description');
	var textarea2 = document.getElementById('contacts');
	textarea.readOnly = true;
	textarea1.readOnly = true;
	textarea2.readOnly = true;
	disableElements()
}

function save(){
		   const titleValue = document.getElementById('title').value;
	       const descriptionValue = document.getElementById('description').value;
	       const contactsValue = document.getElementById('contacts').value;

	       const data = {
	           title: titleValue,
	           description: descriptionValue,
	           contacts: contactsValue,
			   id:id
	       };

	       fetch('/back/Advertisement', {
	           method: 'PUT',
	           headers: {
	               'Content-Type': 'application/json',
	           },
	           body: JSON.stringify(data)
	       }).catch((error) => {
			   console.error('Error:', error);
	       });
		showObsMode()
}

function cancel(){
	var textarea = document.getElementById('title');
	var textarea1 = document.getElementById('description');
	var textarea2 = document.getElementById('contacts');
	textarea.value = str;
	textarea1.value = str1;
	textarea2.value = str2;
	showObsMode()
}