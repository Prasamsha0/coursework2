<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Feedback Management - Admin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />

<main class="admin-container">
  <h1>User Feedback & Messages</h1>
  <table class="admin-table">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Message</th>
        <th>Date Submitted</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Aarav Shrestha</td>
        <td>aarav@gmail.com</td>
        <td>Loved the traditional artwork collection!</td>
        <td>2024-12-12</td>
      </tr>
      <tr>
        <td>Maya Lama</td>
        <td>maya.lama@gmail.com</td>
        <td>Do you offer international shipping?</td>
        <td>2025-01-05</td>
      </tr>
      <tr>
        <td>John Doe</td>
        <td>john@example.com</td>
        <td>I’m interested in custom Mandala artwork.</td>
        <td>2025-03-22</td>
      </tr>
    </tbody>
  </table>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
