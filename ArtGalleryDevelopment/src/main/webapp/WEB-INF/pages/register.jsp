<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Registration Form</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/register.css" />
</head>
<body>
  <div class="container">
    <h1>Registration Form</h1>

    <c:if test="${not empty error}">
      <p class="error-message">${error}</p>
    </c:if>

    <c:if test="${not empty success}">
      <p class="success-message">${success}</p>
    </c:if>

    <form action="${contextPath}/register" method="post">
      
      <div class="row">
        <div class="col">
          <label for="userId">User ID:</label>
          <input type="number" id="userId" name="userId" value="${userId}" required>
        </div>
        <div class="col">
          <label for="userName">Username:</label>
          <input type="text" id="userName" name="userName" value="${userName}" required>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label for="contact">Contact:</label>
          <input type="tel" id="contact" name="contact" value="${contact}" required>
        </div>
        <div class="col">
          <label for="address">Address:</label>
          <input type="text" id="address" name="address" value="${address}" required>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" value="${email}" required>
        </div>
        <div class="col">
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label for="age">Age:</label>
          <input type="number" id="age" name="age" value="${age}" required>
        </div>
        <div class="col">
          <label for="role">Role:</label>
          <input type="text" id="role" name="role" value="${role}" required>
        </div>
      </div>

      <div class="row">
        <div class="col">
          <label for="dob">Date of Birth:</label>
          <input type="date" id="dob" name="dob" value="${dob}" required>
        </div>
        <div class="col">
          <label>Gender:</label><br>
          <input type="radio" id="male" name="gender" value="male" ${gender == 'male' ? 'checked' : ''} required>
          <label for="male">Male</label>
          <input type="radio" id="female" name="gender" value="female" ${gender == 'female' ? 'checked' : ''} required>
          <label for="female">Female</label>
        </div>
      </div>

      <button type="submit">Register</button>
    </form>

    <a href="${contextPath}/login" class="login-button">Login If You Have Already An Account</a>
  </div>
</body>
</html>
