<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gallery - Mandala Studios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css?v=1.0" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<div class="gallery-wrapper">
  <div class="search-bar">
    <input type="text" id="searchInput" placeholder="Search by Artwork or Artist..." />
    <button onclick="searchArtwork()">Search</button>
  </div>

  <h1 class="gallery-title">Our Art Collection</h1>

  <div class="art-grid">
    <c:forEach var="art" items="${artworks}">
      <div class="art-card" data-name="${art.artworkName.toLowerCase()}" data-artist="${art.artistName.toLowerCase()}">
        <div class="art-info">
          <h1 class="art-name">${art.artworkName}</h1>
          <h3 class="art-artist">by ${art.artistName}</h3>
          <p><strong>Date:</strong> ${art.artworkDate}</p>
          <p><strong>Medium:</strong> ${art.artworkMedium}</p>
          <p><strong>Category:</strong> ${art.artworkCategory}</p>
          <p><strong>Format:</strong> ${art.artworkFormat}</p>
          <p><strong>Price:</strong> Rs. ${art.artworkPrice}</p>

          <a href="${pageContext.request.contextPath}/ArtDetailsController?id=${art.artworkId}" class="view-details-btn">View Details</a>	
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

<script>
  function searchArtwork() {
    const query = document.getElementById('searchInput').value.toLowerCase().trim();
    const cards = document.querySelectorAll('.art-card');
    let found = false;

    cards.forEach(card => {
      const name = card.dataset.name;
      const artist = card.dataset.artist;
      
      if (name.includes(query) || artist.includes(query)) {
        card.style.display = 'block';
        found = true;
      } else {
        card.style.display = 'none';
      }
    });

    if (!found && query) {
      alert('No matching artwork found for: ' + query);
    }

    if (!query) {
      cards.forEach(card => card.style.display = 'block');
    }
  }

  // Add event listener for Enter key
  document.getElementById('searchInput').addEventListener('keyup', function(e) {
    if (e.key === 'Enter') {
      searchArtwork();
    }
  });
</script>

</body>
</html>