<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - Mandala Studios</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css?v=1.0"/>
</head>
<body>
  <header>
    <nav class="navbar">
      <div class="logo">MANDALA STUDIOS</div>
      <ul class="nav-links">
		<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/userUpdate">Manage Information</a></li>
        <!-- Fix the double quotes issue -->
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

  <section class="featured-section">
    <h2>Featured Artworks</h2>
    <div class="carousel">
      <button class="left-arrow">&#10094;</button>
      <img src="images/featured.png" alt="Featured Art">
      <button class="right-arrow">&#10095;</button>
    </div>
  </section>

  <section class="top-rated">
    <h3>Top Rated</h3>
    <div class="artworks">
      <div class="art-item">
        <img src="images/sanvij.png" alt="Sanjiv Gurung">
        <p>Sanjiv Gurung</p>
      </div>
      <div class="art-item">
        <img src="images/devi.png" alt="Devi Sakya">
        <p>Devi Sakya</p>
      </div>
      <div class="art-item">
        <img src="images/krishna.png" alt="Krishna Rai">
        <p>Krishna Rai</p>
      </div>
    </div>
  </section>

  <section class="abstract-section">
    <div class="abstract-img">
      <img src="images/abstract.png" alt="Abstract Art">
    </div>
    <div class="abstract-text">
      <h2>abstract arts</h2>
      <p>Abstract art is a visual language of shapes, colors, and forms that goes beyond realistic representation. It invites viewers to feel and interpret freely, sparking imagination and emotion without needing a clear subject.</p>
      <button>see more</button>
    </div>
  </section>

  <section class="buddhist-art">
    <h3>Buddhist art works</h3>
    <div class="buddha-art">
      <img src="images/buddha1.png" alt="Buddha Art 1">
      <img src="images/buddha2.png" alt="Buddha Art 2">
      <img src="images/buddha3.png" alt="Buddha Art 3">
      <img src="images/buddha4.png" alt="Buddha Art 4">
    </div>
  </section>

  <footer>
    <div class="footer-content">
      <div class="faq">
        <h4>FAQs</h4>
        <p>Where are we located?<br>
        What are our prices like?<br>
        Are guided tours available?<br>
        Who do I contact for more information?<br>
        What are the gallery's opening hours?</p>
      </div>
      <div class="contact">
        <h4>Contact US</h4>
        <p>Kathmandu , Thapathali<br>
        98123456789, 01-4345672<br>
        mandalastudios@icloud.com</p>
      </div>
    </div>
    <div class="footer-bottom">
      <div class="brand">MANDALA STUDIOS</div>
      <p>2025 Mandala Studios. All Rights Reserved.</p>
      <div class="socials">
        <img src="images/tiktok-icon.png" alt="TikTok">
        <img src="images/facebook-icon.png" alt="Facebook">
        <img src="images/instagram-icon.png" alt="Instagram">
        <img src="images/youtube-icon.png" alt="YouTube">
      </div>
    </div>
  </footer>
</body>
</html>
