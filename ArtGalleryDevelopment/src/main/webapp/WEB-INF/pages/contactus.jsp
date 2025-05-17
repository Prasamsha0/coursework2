<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - Mandala Studios</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contactus.css?v=1.0"/>
    
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="contact-wrapper">
  <div class="contact-container">
    <div class="contact-form">
      <div class="form-header">
        <h1>Get in Touch</h1>
        <p class="subtitle">Have questions about our artwork? We'd love to hear from you.</p>
      </div>
      <form id="contactForm">
        <div class="form-group">
          <label for="name">Full Name</label>
          <input type="text" id="name" name="name" placeholder="Enter your name" required />
        </div>
        <div class="form-group">
          <label for="email">Email Address</label>
          <input type="email" id="email" name="email" placeholder="Enter your email" required />
        </div>
        <div class="form-group">
          <label for="message">Your Message</label>
          <textarea id="message" name="message" placeholder="Tell us about your inquiry..." rows="5" required></textarea>
        </div>
        <button type="submit" class="submit-btn">
          <span>Send Message</span>
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
            <path d="M5 12H19M19 12L12 5M19 12L12 19" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </button>
      </form>
    </div>
    <div class="contact-info">
      <div class="image-container">
        <img src="${contextPath}/images/img3.png" alt="Mandala Artwork" class="featured-image">
        <div class="info-overlay">
          <h3>Visit Our Studio</h3>
          <p>Kathmandu, Thapathali<br>98123456789, 01-4345672<br>mandalastudios@icloud.com</p>
          <div class="social-links">
            <a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
            <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
            <a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

<script>
  document.getElementById('contactForm').addEventListener('submit', function(e) {
    e.preventDefault();
    // Add form submission logic here
    alert('Thank you for your message! We will get back to you soon.');
    this.reset();
  });
</script>
</body>
</html>