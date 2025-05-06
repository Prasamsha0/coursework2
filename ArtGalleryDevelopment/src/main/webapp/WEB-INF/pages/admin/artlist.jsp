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

<section class="dashboard-container">
    <h1>All Artworks</h1>
    <table border="1" cellspacing="0" cellpadding="10" width="100%">
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
            <tr>
                <td>${art.artworkId}</td>
                <td>${art.artworkName}</td>
                <td>${art.artistName}</td>
                <td>${art.artworkDate}</td>
                <td>${art.artworkMedium}</td>
                <td>${art.artworkPrice}</td>
                <td>${art.artworkCategory}</td>
                <td>${art.artworkFormat}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/delete-artwork" method="post" onsubmit="return confirm('Are you sure you want to delete this artwork?');">
                        <input type="hidden" name="artworkId" value="${art.artworkId}" />
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
