<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile - Mandala Studios</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/update.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp" />

<main class="main-container">
    <div class="profile-wrapper">

        <!-- Profile Picture -->
        <div class="profile-pic-section">
            <img src="${contextPath}/images/default-profile.png" alt="Profile Picture" class="profile-pic"/>
            <p class="change-photo">Change photo (coming soon)</p>
        </div>

        <!-- Profile Form -->
        <div class="form-section">
            <h2>Edit Your Profile</h2>
            <c:if test="${not empty successMessage}">
    		<p class="success-message" style="color: green;">${successMessage}</p>
			</c:if>

			<c:if test="${not empty errorMessage}">
   			<p class="error-message" style="color: red;">${errorMessage}</p>
			</c:if>
            
            <form action="${contextPath}/userUpdate" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="userName" value="${user.userName}" required />
                    </div>
                    <div class="form-group">
                        <label>Age</label>
                        <input type="number" name="age" value="${user.age}" required />
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" value="${user.email}" required />
                    </div>
                    <div class="form-group">
                        <label>DOB</label>
                        <input type="date" name="dob" value="${user.dob}" required />
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Contact</label>
                        <input type="text" name="contact" value="${user.contact}" required />
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" value="${user.address}" required />
                    </div>
                </div>
                <div class="form-row">
    		<div class="form-group">
        		<label>New Password (optional)</label>
        		<input type="password" name="password" placeholder="Enter new password" />
    </div>
    <div class="form-group">
        <label>Retype Password</label>
        <input type="password" name="repass" placeholder="Retype new password" />
    </div>
</div>

                <button type="submit">Save</button>
            </form>
        </div>

    </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
