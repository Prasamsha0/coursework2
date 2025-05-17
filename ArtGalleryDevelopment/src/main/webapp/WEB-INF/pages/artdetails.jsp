<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${art.artworkName} - Mandala Studios</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/artdetails.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="art-detail-container">
  <div class="art-description">
    <h1>${art.artworkName}</h1>
    <h3>by ${art.artistName}</h3>
    <p><strong>Date:</strong> ${art.artworkDate}</p>
    <p><strong>Medium:</strong> ${art.artworkMedium}</p>
    <p><strong>Category:</strong> ${art.artworkCategory}</p>
    <p><strong>Format:</strong> ${art.artworkFormat}</p>
    <p><strong>Price:</strong> Rs. ${art.artworkPrice}</p>

    <a href="${pageContext.request.contextPath}/ProductController" class="back-btn">← Back to Gallery</a>
  </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
