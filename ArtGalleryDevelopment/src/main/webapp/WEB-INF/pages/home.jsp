<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession, jakarta.servlet.http.HttpServletRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.adg.util.CookieUtil" %>
<%
    HttpSession userSession = request.getSession(false);
    String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
    pageContext.setAttribute("currentUser", currentUser);
    HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
    if (userSession == null || currentUser == null || CookieUtil.getCookie(req, "role") == null) {
        ((HttpServletResponse) pageContext.getResponse()).sendRedirect(req.getContextPath() + "/login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Mandala Studios - Home</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" />
  <script defer src="${pageContext.request.contextPath}/js/lightbox.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<!-- Featured Section -->
<section class="featured">
  <div class="featured-text">
    <h1>Explore Timeless Nepali Art</h1>
    <p>Discover cultural expressions through brush and pigment, captured by the finest Nepali artists.</p>
    <a href="${pageContext.request.contextPath}/ProductController" class="logout-btn">>Browse Gallery</a>
  </div>
  <div class="featured-image">
    <img src="images/featured.png" alt="Featured Art">
  </div>
</section>

<!-- Top Rated Artworks -->
<section class="artwork-section">
  <h2>Top Rated</h2>
  <div class="art-grid">
    <div class="art-card">
      <img src="images/krishna.png" alt="Krishna Rai" onclick="openLightbox(this)">
      <div class="info">
        <p><strong>Artist:</strong> Krishna Rai</p>
        <p><strong>Price:</strong> NPR 12,500</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Watercolor</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/devi.png" alt="Devi Sakya" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Devi Sakya</p>
        <p><strong>Price:</strong> NPR 18,000</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Acrylic</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/sanvij.png" alt="Sanjiv Gurung" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Sanjiv Gurung</p>
        <p><strong>Price:</strong> NPR 15,000</p>
        <p><strong>Rating:</strong> ★★★★☆</p>
        <p><strong>Medium:</strong> Oil on Canvas</p>
      </div>
    </div>
  </div>
</section>

<!-- Buddhist Artworks -->
<section class="artwork-section alt-bg">
  <h2>Buddhist Artworks</h2>
  <div class="art-grid">
    <div class="art-card">
      <img src="images/buddha1.png" alt="Buddha 1" onclick="openLightbox(this)">
      <div class="info">
        <p><strong>Artist:</strong> Lama Tashi</p>
        <p><strong>Price:</strong> NPR 20,000</p>
        <p><strong>Rating:</strong> ★★★★☆</p>
        <p><strong>Medium:</strong> Thangka</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/buddha2.png" alt="Buddha 2" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Pema Doma</p>
        <p><strong>Price:</strong> NPR 25,000</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Natural Pigment</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/buddha3.png" alt="Buddha 3" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Karma Dorje</p>
        <p><strong>Price:</strong> NPR 19,000</p>
        <p><strong>Rating:</strong> ★★★★☆</p>
        <p><strong>Medium:</strong> Acrylic</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/buddha4.png" alt="Buddha 4" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Sonam Tamang</p>
        <p><strong>Price:</strong> NPR 20,500</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Mixed Media</p>
      </div>
    </div>
	<div class="art-card">
      <img src="images/buddha5.png" alt="Buddha 5" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Sonam Tamang</p>
        <p><strong>Price:</strong> NPR 20,500</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Mixed Media</p>
      </div>
    </div>
    <div class="art-card">
      <img src="images/buddha6.png" alt="Buddha 4" onclick="openModal(this.src)">
      <div class="info">
        <p><strong>Artist:</strong> Sonam Tamang</p>
        <p><strong>Price:</strong> NPR 20,500</p>
        <p><strong>Rating:</strong> ★★★★★</p>
        <p><strong>Medium:</strong> Mixed Media</p>
      </div>
    </div>
  </div>
</section>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

<!-- Lightbox Modal -->
<div id="lightbox" onclick="closeLightbox()">
  <img id="lightbox-img" src="" alt="Enlarged Art">
</div>

</body>
</html>