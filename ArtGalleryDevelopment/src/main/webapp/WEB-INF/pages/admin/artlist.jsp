<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Artworks - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/artlist.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />
<div class="search-bar">
  <input type="text" id="searchInput" placeholder="Search by Artwork Name or Artist..." />
  <button onclick="searchArtwork()">Search</button>
</div>

<section class="dashboard-container">
    <h1>All Artworks</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Artist</th>
                <th>Date</th>
                <th>Medium</th>
                <th>Price</th>
                <th>Category</th>
                <th>Format</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="art" items="${artworks}">
             <form action="${pageContext.request.contextPath}/artlist" method="post">
            <tr>
                    <td>
                        <input type="hidden" name="artworkId" value="${art.artworkId}" />
                        ${art.artworkId}
                    </td>
                    <td><input type="text" name="artworkName" value="${art.artworkName}" /></td>
                    <td><input type="text" name="artistName" value="${art.artistName}" /></td>
                    <td><input type="date" name="artworkDate" value="${art.artworkDate}" /></td>
                    <td><input type="text" name="artworkMedium" value="${art.artworkMedium}" /></td>
                    <td><input type="number" name="artworkPrice" step="0.01" value="${art.artworkPrice}" /></td>
                    <td>
					<select name="artworkCategory" required>
					    <option value="Contemporary" ${art.artworkCategory == 'Contemporary' ? 'selected' : ''}>Contemporary</option>
					    <option value="Traditional" ${art.artworkCategory == 'Traditional' ? 'selected' : ''}>Traditional</option>
					    <option value="Buddhist" ${art.artworkCategory == 'Buddhist' ? 'selected' : ''}>Buddhist</option>
					    <option value="Abstract" ${art.artworkCategory == 'Abstract' ? 'selected' : ''}>Abstract</option>
					  </select>
					</td>
					<td>
					  <select name="artworkFormat" required>
					    <option value="Canvas" ${art.artworkFormat == 'Canvas' ? 'selected' : ''}>Canvas</option>
					    <option value="Scroll" ${art.artworkFormat == 'Scroll' ? 'selected' : ''}>Scroll</option>
					    <option value="Digital" ${art.artworkFormat == 'Digital' ? 'selected' : ''}>Digital</option>
					    <option value="Framed" ${art.artworkFormat == 'Framed' ? 'selected' : ''}>Framed</option>
					  </select>
					</td>
					                    
                    <td>
                        <button type="submit" style="background-color:#3498db;">Update</button>
                        <a href="${pageContext.request.contextPath}/artlist?action=delete&artworkId=${art.artworkId}" onclick="return confirm('Are you sure you want to delete this artwork?');">
                            <button type="button" style="background-color:#e74c3c;">Delete</button>
                        </a>
                    </td>
            </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</section>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
<script>
  document.getElementById('searchInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
      e.preventDefault();
      searchArtwork();
    }
  });

  function searchArtwork() {
    const query = document.getElementById('searchInput').value.toLowerCase().trim();
    const rows = document.querySelectorAll('table tbody tr');
    let found = false;

    rows.forEach(row => {
      row.classList.remove('highlighted-row');
      const nameInput = row.querySelector('input[name="artworkName"]');
      const artistInput = row.querySelector('input[name="artistName"]');
      if (nameInput && artistInput) {
        const name = nameInput.value.toLowerCase();
        const artist = artistInput.value.toLowerCase();

        if (name.includes(query) || artist.includes(query)) {
          if (!found) {
            row.scrollIntoView({ behavior: 'smooth', block: 'center' });
          }
          row.classList.add('highlighted-row');
          found = true;
        }
      }
    });

    if (!query) {
      rows.forEach(row => row.classList.remove('highlighted-row'));
    }

    if (!found && query) {
      alert('No matching artwork found.');
    }
  }
</script>

</body>
</html>
