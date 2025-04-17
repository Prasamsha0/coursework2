<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page!</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
	<div class="main">
		<div class="picture">
		</div>
		<div class="loginform">
			<div class="row">
				  <form action="login" method="post">
			      <h1>Login</h1>
			
			      <label for="username">Username</label>
			      <input type="text" id="username" name="username" required>
			
			      <label for="password">Password</label>
			      <input type="password" id="password" name="password" required>
			
			      <button type="submit">Login</button>
		    	<a href="${pageContext.request.contextPath}/register">Register Your New Account</a>
			      
			</div>
		</div>
	</div>
</body>
</html>
