<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User List - Admin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userlist.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />

<section class="dashboard-container">
  <h1>Registered Users</h1>
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Contact</th>
        <th>Address</th>
        <th>Email</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Date of Birth</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="user" items="${users}">
        <tr>
          <td>${user.userId}</td>
          <td>${user.userName}</td>
          <td>${user.contact}</td>
          <td>${user.address}</td>
          <td>${user.email}</td>
          <td>${user.age}</td>
          <td>${user.gender}</td>
          <td>${user.dob}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</section>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
