<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value='/css/header.css' />" />
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">

<header class="navbar">
  <div class="logo">🎨 Mandala Studios</div>
  <nav>
    <ul class="nav-links">
      <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/userUpdate">Manage Info</a></li>
      <li><a href="#">Gallery</a></li>
      <li><a href="#">Events</a></li>
      <li><a href="${pageContext.request.contextPath}/AboutController">About Us</a></li>
      <li><a href="${pageContext.request.contextPath}/ContactController">Contact Us</a></li>
    </ul>
  </nav>
  <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a>
</header>
