<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration Form</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/register.css?v=1.0" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
  <div class="image-section">
    <img src="${contextPath}/images/register.png" alt="Design Illustration" />
  </div>
  <div class="form-section">
    <h1>Create Your Account</h1>

    <c:if test="${not empty error}">
      <p class="error-message">${error}</p>
    </c:if>

    <c:if test="${not empty success}">
      <p class="success-message">${success}</p>
    </c:if>
    <form action="${contextPath}/register" method="post" enctype="multipart/form-data">
    
      <div class="row">
        <input type="text" name="userName" placeholder="Full Name" value="${username}" required />
      </div>

      <div class="row">
        <input type="tel" name="contact" placeholder="Contact" value="${contact}" required />
        <input type="text" name="address" placeholder="Address" value="${address}" required />
      </div>

      <div class="row">
        <input type="email" name="email" placeholder="Email" value="${email}" required />
        <input type="password" name="password" placeholder="Password" required />
      </div>

      <div class="row">
        <input type="number" name="age" placeholder="Age" value="${age}" required />
        <input type="password" name="repass" placeholder="Re-enter password" value="${repass}" required />
      </div>

      <div class="row">
        <input type="date" name="dob" placeholder="Date of Birth" value="${dob}" required />
        <div class="gender-group">
          <label>
            <input type="radio" name="gender" value="male"
                   <c:if test="${gender == 'male'}">checked</c:if> required> Male
          </label>
          <label>
            <input type="radio" name="gender" value="female"
                   <c:if test="${gender == 'female'}">checked</c:if> required> Female
          </label>
        </div>
      </div>
    <div class="row">
 		 <label>Upload Profile Photo</label>
  		<input type="file" name="profileImage" accept="image/*" required />
	</div>
      

      <button type="submit">Register</button>
    </form>

    <p class="terms">By registering, you agree to our terms and conditions</p>

    <div class="oauth">
      <img src="${contextPath}/images/google.png" alt="Google" />
      <img src="${contextPath}/images/apple.png" alt="Apple" />
    </div>

    <p class="login-link">Already have an account? <a href="${contextPath}/login">Login here</a></p>
  </div>
</div>
</body>
</html>
