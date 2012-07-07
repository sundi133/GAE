<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.activibe.gae.java.model.Todo" %>
<%@ page import="com.activibe.gae.java.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>MyTypeOfJob</title>

<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>

<div id="contact">
	<h1>Find Quality Startups</h1>
	<form action="/new" enctype="multipart/form-data" method="post">
		<fieldset>

				<label for="email">Resume</label>
				<input type="file" name="datafile" size="40">
				<input type="hidden" name="resume" id="resume" size="1" value="1">			
			<div>
				<input type="submit" value="Find">
			</div>

			
		</fieldset>
	</form>
</div>

</body>
</html>
