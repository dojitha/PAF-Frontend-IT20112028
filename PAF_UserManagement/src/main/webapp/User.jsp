<%@page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<h1>User Management</h1>
	
		<form id="formNotice" name="formNotice" method="post" action="Payments.jsp">
			User ID:<input id="userID" name="userID" type="text" class="form-control"><br>
			Customer Name:<input id="username" name="username" type="text" class="form-control"><br>
			Birth Day:<input id="birthday" name="birthday" type="text" class="form-control"><br>
			Mail:<input id="email" name="email" type="text" class="form-control"><br>
			Contact Number:<input id="cnumber" name="cnumber" type="text" class="form-control"><br>
			
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
		</form>
	
	<br>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<div id="divItemsGrid">
	<%
	User noteObj = new User();
 		out.print(noteObj.readPay());
	%>
	</div>
	
	</div>
</body>
</html>