<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us - Mandala Studios</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" href="${contextPath}/css/about.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="about-container">
    <div class="about-top">
        <img src="${contextPath}/images/img2.png" alt="Art Piece" class="art-image">
        <div class="about-text">
            <h2>About Us</h2>
            <p>
                Established in the 1950s, Mandala Art Gallery is one of the oldest and most renowned art galleries in Nepal.
                Located in the heart of Kathmandu, the gallery has been a hub for art enthusiasts, offering a unique blend of traditional and contemporary artworks.
                With a special focus on the intricate and mesmerizing Mandala art, the gallery has become a symbol of Nepal’s rich artistic heritage.
                Through the years, Mandala Art Gallery has continued to showcase exceptional works that celebrate the cultural and spiritual essence of Nepal,
                making it a must-visit destination for art lovers from around the world.
            </p>
        </div>
    </div>

    <div class="about-bottom">
        <img src="${contextPath}/images/img1.png" alt="Gallery Interior">
        <div class="welcome-text">
            <h2>Welcome to Mandala Art Point</h2>
            <p>
                This admin Panel is designed to help or manage the art works effectively and efficiently.
                With functions like add, update and delete, you can easily manage the paintings with ease.
            </p>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
    