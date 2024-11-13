<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 
.categoriesContainer{
		  	display: flex;
            width: 90%;
            margin: 0 auto;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: white;
            flex-direction: row;
    		align-items: flex-start;
    		
    overflow: hidden;
}
.subCategory{
	  overflow-y: auto;
	  width: 25%;
	  border-right: 1px solid #ddd;
	  align-items: center;
	  flex-direction: column;
	  display: flex;
	  max-height: 250px;
}
.radioContainer{
	width: 88%;
    padding: 5px;
    border: 1px solid black; 
    margin: 3px;
    border-radius: 5px;
}
.radioContainer:hover{
    background-color: #ddd; 
    transition: background-color 0.3s, color 0.3s;
}
</style>
</head>
<body>

<div class="categoriesContainer" id="catCon">

</div>

<script>
    let tags = [];
    function start(callback){
		 fetch('<%= request.getContextPath() %>/getTags')
        .then(response => response.json())
        .then(data => {
            tags = data;
            initialize();
            callback();
        })
        .catch(error => console.error('Ошибка при получении данных:', error))
    }
    
    function initialize(){
	         initializeFirstColumn()
	         <% if (request.getAttribute("currentTag") != null){
	        	 System.out.println((String)request.getAttribute("currentTag"));
	        	 %>
	        	 chooseByName('<%= (String)request.getAttribute("currentTag")%>')
	         <%
	         }else{%>
		         const urlParams = new URLSearchParams(window.location.search)
		         if(urlParams.has('tags')){
		        		chooseByName(urlParams.get('tags'))
	        	 }	 
	         <%
	         }
	         %>
		}    
	
    function disableElements(){
    const elements = document.querySelectorAll('.radioButton');
 	   elements.forEach(element => {
 	   element.disabled = true;
 	   });
    }
    
    function enableElements(){
    const elements = document.querySelectorAll('.radioButton');
  	   elements.forEach(element => {
  	   element.disabled = false;
  	   });
    }
    
	function chooseByName(name){
	 	var currentTag =  tags.find(element => element.categoryName === name);
	 	if(currentTag !== undefined){
			let initTags = []
			while(currentTag.parentName !== null){
				initTags.push(currentTag.categoryName)
				currentTag =  tags.find(element => element.categoryName === currentTag.parentName);
			}
			initTags.reverse();
			for (let i = 0; i < initTags.length; i++) {
				 const container = document.getElementById('catCon');
				 const subCategoryDiv = document.createElement('div');
			     subCategoryDiv.className = 'subCategory';
			     subCategoryDiv.setAttribute('data-column', i+2);
				 
			     var curTag = tags.find(element => element.categoryName === initTags[i]);
			     curTag.childCategories.forEach(childCategory => {
			            const radioContainer = document.createElement('div');
			            radioContainer.className = 'radioContainer';

			            const radioButton = document.createElement('input');
			            radioButton.type = 'radio';
			            radioButton.className = 'radioButton';
			            radioButton.name = 'tags';
			            radioButton.value = childCategory;
			            radioButton.id = childCategory;
			            radioButton.onclick = () => choose(radioButton);

			            const label = document.createElement('label');
			            label.htmlFor = childCategory;
			            label.textContent = childCategory;

			            radioContainer.appendChild(radioButton);
			            radioContainer.appendChild(label);
			            subCategoryDiv.appendChild(radioContainer);
			        });
			     
			     container.appendChild(subCategoryDiv);
			}
			document.getElementById(name).checked = true;
		}
		
		
	}
    
    function initializeFirstColumn() {
        const container = document.getElementById('catCon');
        const subCategoryDiv = document.createElement('div');
        subCategoryDiv.className = 'subCategory';
        subCategoryDiv.setAttribute('data-column', 1);
        const firstTag = tags[0];

        firstTag.childCategories.forEach(childCategory => {
            const radioContainer = document.createElement('div');
            radioContainer.className = 'radioContainer';

            const radioButton = document.createElement('input');
            radioButton.type = 'radio';
            radioButton.className = 'radioButton';
            radioButton.name = 'tags';
            radioButton.value = childCategory;
            radioButton.id = childCategory;
            radioButton.onclick = () => choose(radioButton);

            const label = document.createElement('label');
            label.htmlFor = childCategory;
            label.textContent = childCategory;

            radioContainer.appendChild(radioButton);
            radioContainer.appendChild(label);
            subCategoryDiv.appendChild(radioContainer);
        });

        container.appendChild(subCategoryDiv);
    }
    
    function choose(element) {
        var par = element.parentElement.parentElement;
        const columnIndex = parseInt(par.getAttribute('data-column'));
        const columns = document.querySelectorAll('.subCategory');
        
        columns.forEach(column => {
            const columnNumber = parseInt(column.getAttribute('data-column'));
            if (columnNumber > columnIndex) {
                column.remove();
            }
        });

        const categoryName = element.id;

        const category = tags.find(tag => tag.categoryName === categoryName);

        if (category) {
            const container = document.getElementById('catCon');
            const newColumnIndex = columnIndex + 1;
            
            const subCategoryDiv = document.createElement('div');
            subCategoryDiv.className = 'subCategory';
            subCategoryDiv.setAttribute('data-column', newColumnIndex);

            category.childCategories.forEach(childCategory => {
                const radioContainer = document.createElement('div');
                radioContainer.className = 'radioContainer';

                const radioButton = document.createElement('input');
                radioButton.type = 'radio';
                radioButton.className = 'radioButton';
                radioButton.name = 'tags';
                radioButton.value = childCategory;
                radioButton.id = childCategory;
                radioButton.onclick = () => choose(radioButton);

                const label = document.createElement('label');
                label.htmlFor = childCategory;
                label.textContent = childCategory;

                radioContainer.appendChild(radioButton);
                radioContainer.appendChild(label);
                subCategoryDiv.appendChild(radioContainer);
            });

            container.appendChild(subCategoryDiv);
        }
    }
    
    function getTags() {
        const selectedTags = [];
        const selectedRadios = document.querySelectorAll('.radioButton:checked');

        selectedRadios.forEach(radio => {
            selectedTags.push(radio.id);
        });

        return selectedTags;
    }

</script>
</body>
</html>