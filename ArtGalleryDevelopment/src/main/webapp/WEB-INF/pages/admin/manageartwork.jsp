<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Manage Artwork</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/manageartwork.css?v=1.0" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />
<div class="container">
  <div class="image-section">
    <img src="${contextPath}/images/art_form.png" alt="Artwork Illustration" />
  </div>

  <div class="form-section">
    <h1>Add New Artwork</h1>

    <c:if test="${not empty error}">
      <p class="error-message">${error}</p>
    </c:if>

    <c:if test="${not empty success}">
      <p class="success-message">${success}</p>
    </c:if>


	<form action="${contextPath}/ManageArtwork" method="post">
  <div class="row">
    <input type="text" name="artworkName" placeholder="Artwork Name" value="${artworkName}" required />
  </div>

  <div class="row">
    <input type="text" name="artistName" placeholder="Artist Name" value="${artistName}" required />
    <input type="date" name="artworkDate" value="${artworkDate}" required />
  </div>

  <div class="row">
    <input type="text" name="artworkMedium" placeholder="Medium" value="${artworkMedium}" required />
    <input type="number" name="artworkPrice" placeholder="Price (NPR)" value="${artworkPrice}" required />
  </div>

  <div class="row">
    <select name="artworkCategory" required>
      <option value="" disabled selected>Select Category</option>
      <option value="Contemporary">Contemporary</option>
      <option value="Traditional">Traditional</option>
      <option value="Buddhist">Buddhist</option>
      <option value="Abstract">Abstract</option>
    </select>

    <select name="artworkFormat" required>
      <option value="" disabled selected>Select Format</option>
      <option value="Canvas">Canvas</option>
      <option value="Scroll">Scroll</option>
      <option value="Digital">Digital</option>
      <option value="Framed">Framed</option>
    </select>
  </div>

  <button type="submit">Save Artwork</button>
	</form>
	
  </div>
</div>
  <jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
