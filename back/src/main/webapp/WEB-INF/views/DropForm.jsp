<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
		<select class="search-dropdown" name="tags" onchange="removeDropdown(this)">
		    <option value="" <%= request.getParameter("tags") == null || request.getParameter("tags").isEmpty() ? "selected" : "" %>>Выберите опцию</option>
		    <option value="option1">Очистить</option>
		    <option value="option2" <%= "option1".equals(request.getParameter("tags")) ? "selected" : "" %>>Опция 1</option>
		    <option value="option3" <%= "option2".equals(request.getParameter("tags")) ? "selected" : "" %>>Опция 2</option>
		    <option value="option4" <%= "option3".equals(request.getParameter("tags")) ? "selected" : "" %>>Опция 3</option>
		</select>
</html>
