<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css?v=1.0"/>
</head>
<body>
  <header>
    <nav class="navbar">
      <div class="logo">MANDALA STUDIOS</div>
      <ul class="nav-links">
        <li><a href="#">Home</a></li>
        <li><a href="#">Manage Information</a></li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Contact Us</a></li>
      </ul>
      <div class="search-bar">
        <input type="text" placeholder="search">
        <button>X</button>
      </div>
      <div class="logout">
      <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
      </div>
    </nav>
  </header>
</body>
</html>
