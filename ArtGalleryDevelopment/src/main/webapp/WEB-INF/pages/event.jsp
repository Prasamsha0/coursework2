<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Events & Exhibitions - Mandala Studios</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" href="${contextPath}/css/event.css?v=1.0" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="events-container">
  <div class="events-header">
    <h1>Upcoming Events & Exhibitions</h1>
    <p>Explore the latest exhibitions, workshops, and cultural programs hosted at Mandala Art Gallery.</p>
  </div>

  <div class="events-grid">
    <!-- Event Card -->
    <div class="event-card">
      <img src="${contextPath}/images/image1.jpg" alt="Mandala Showcase" class="event-image" />
      <div class="event-details">
        <h2>Mandala Art Showcase 2025</h2>
        <p class="event-date">📅 June 10 – June 25, 2025</p>
        <p>Experience traditional and modern mandala masterpieces from renowned artists.</p>
      </div>
    </div>

    <div class="event-card">
      <img src="${contextPath}/images/image2.jpg" alt="Art Therapy" class="event-image" />
      <div class="event-details">
        <h2>Art Therapy Workshop</h2>
        <p class="event-date">📅 July 15, 2025</p>
        <p>A hands-on session to experience the calming power of mandala painting.</p>
      </div>
    </div>

    <div class="event-card">
      <img src="${contextPath}/images/image3.jpeg" alt="Art Meets Sound" class="event-image" />
      <div class="event-details">
        <h2>Cultural Evening: Art Meets Sound</h2>
        <p class="event-date">📅 August 8, 2025</p>
        <p>A fusion of traditional instruments and live mural creation on-site.</p>
      </div>
    </div>
  </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
