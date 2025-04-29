<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Profile - Mandala Studios</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/update.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<header>
    <div class="navbar">
        <div class="logo">MANDALA STUDIOS</div>
        <nav>
            <a href="#">Home</a>
            <a href="#">Manage Information</a>
            <a href="#">About Us</a>
            <a href="#">Contact Us</a>
        </nav>
        <input type="search" placeholder="search" />
    </div>
</header>

<main class="main-container">
    <div class="content-wrapper">
        <!-- Left: Form -->
        <div class="form-container">
            <div class="user-icon">👤</div>
            <h2>Username</h2>
            <form action="${contextPath}/userUpdate" method="post">
			    <div class="form-row">
			        <label>Name</label>
			        <input type="text" name="userName" value="${user.userName}" required />
			        <label>Age</label>
			        <input type="number" name="age" value="${user.age}" required />
			    </div>
			
			    <div class="form-row">
			        <label>Email</label>
			        <input type="email" name="email" value="${user.email}" required />
			        <label>Contact</label>
			        <input type="tel" name="contact" value="${user.contact}" required />
			    </div>
			
			    <div class="form-row">
			        <label>Contact</label>
			        <input type="text" name="contact" value="${user.contact}" required />
			        <label>Address</label>
			        <input type="text" name="address" value="${user.address}" required />
			    </div>
			
			    <div class="form-row single">
			        <label>DOB</label>
			        <input type="date" name="dob" value="${user.dob}" required />
			    </div>
			
			    <div class="button-row">
			        <button type="submit">Save</button>
			    </div>
			</form>
			            
        </div>

        <!-- Right: Info box -->
        <div class="info-box">
            <h3>Current Information</h3>
            <p><strong>Name:</strong> ${user.userName}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Contact:</strong> ${user.contact}</p>
            <p><strong>Age:</strong> ${user.age}</p>
            <p><strong>Address:</strong> ${user.address}</p>
        </div>
    </div>
</main>

<footer>
    <div class="footer-content">
        <div class="faq">
            <h4>FAQs</h4>
            <ul>
                <li>Where are we located?</li>
                <li>What are our prices like?</li>
                <li>Are guided tours available?</li>
                <li>Who do I contact for more information?</li>
                <li>What are the gallery’s opening hours?</li>
            </ul>
        </div>
        <div class="contact">
            <h4>Contact US</h4>
            <p>Kathmandu , Thapathali</p>
            <p>98123456789, 01-4345672</p>
            <p>mandalastudios@icloud.com</p>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="footer-left">MANDALA STUDIOS</div>
        <div class="footer-right">
            <p>© 2025 Mandala Studios. All Rights Reserved.</p>
            <div class="socials">
                <span>📘</span><span>🎵</span><span>▶️</span><span>📸</span>
            </div>
        </div>
    </div>
</footer>

</body>
</html>
