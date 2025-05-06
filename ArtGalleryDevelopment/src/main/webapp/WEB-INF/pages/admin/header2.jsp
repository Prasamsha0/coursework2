<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value='/css/header.css' />" />
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">

<header class="navbar">
  <div class="logo">🎨 Mandala Studios</div>
  <nav>
    <ul class="nav-links">
      <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
      <li><a href="${pageContext.request.contextPath}/ManageArtwork">Manage Artwork</a></li>
      <li><a href="${pageContext.request.contextPath}/artlist">View Artworks</a></li>
      <li><a href="#">Gallery</a></li>
      <li><a href="#">Events</a></li>
      <li><a href="#">About Us</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>
  </nav>
  <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a>
</header>
