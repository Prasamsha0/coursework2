<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Our Artists - Mandala Studios</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" href="${contextPath}/css/artist.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="artists-section">
  <div class="container">
    <h1>Meet Our Artists</h1>
    <p class="intro-text">
      The heart of Mandala Studios lies in the hands of our talented artists. Their passion brings every artwork to life.
    </p>

    <div class="artist-grid">
      <div class="artist-card">
        <img src="${contextPath}/images/artist1.jpg" alt="Aarav Shrestha" />
        <h3>Aarvi Shrestha</h3>
        <p>Traditional Mandala artist specializing in canvas scrolls using natural pigments and symbolism.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist2.jpg" alt="Maya Lama" />
        <h3>Maya Lama</h3>
        <p>Contemporary painter known for abstract forms and bold storytelling through colors.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist3.jpg" alt="Raj Karki" />
        <h3>Raj Karki</h3>
        <p>Expert in digital formats blending sacred geometry with modern minimalist themes.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist4.jpg" alt="Sarita Rana" />
        <h3>Haya Miyuzaki</h3>
        <p>Oil-based mural artist capturing Buddhist heritage through vibrant framed work.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist5.jpg" alt="Nima Sherpa" />
        <h3>Nima Sherpa</h3>
        <p>Mixed media artist infusing Himalayan textures with spiritual themes and modern interpretation.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist6.jpg" alt="Pema Gurung" />
        <h3>Pema Gurung</h3>
        <p>Ink sketch specialist drawing intricate mandala outlines rich in folklore and tradition.</p>
      </div>

      <div class="artist-card">
        <img src="${contextPath}/images/artist7.jpg" alt="Kiran Manandhar" />
        <h3>Kiran Manandhar</h3>
        <p>Renowned for layered brushwork, blending nature motifs with sacred patterns.</p>
      </div>

      <!-- New artist 4 -->
      <div class="artist-card">
        <img src="${contextPath}/images/artist8.jpg" alt="Laxmi Basnet" />
        <h3>Laxmi Basnet</h3>
        <p>Combines beadwork and painting to create tactile mandalas on fabric canvases.</p>
      </div>
    </div>
  </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
