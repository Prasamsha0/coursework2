<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User List - Admin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userlist.css?v=1.0" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />
<section class="dashboard-container">
  <h1>Registered Users</h1>

  <div class="user-card-grid">
    <c:forEach var="user" items="${users}">
      <div class="user-card">
        <h2>${user.userName}</h2>
        <p><strong>Contact:</strong> ${user.contact}</p>
        <p><strong>Address:</strong> ${user.address}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Age:</strong> ${user.age}</p>
        <p><strong>Gender:</strong> ${user.gender}</p>
        <p><strong>Date of Birth:</strong> ${user.dob}</p>
      </div>
    </c:forEach>
  </div>
</section>


<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
