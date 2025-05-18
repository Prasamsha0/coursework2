<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Sales Report - Admin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" />
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />

<main class="admin-container">
  <h1>Artwork Sales Report</h1>
  <div class="report-boxes">
    <div class="report-box">
      <h3>Total Artworks Listed</h3>
      <p>48</p>
    </div>
    <div class="report-box">
      <h3>Total Sales</h3>
      <p>Rs. 1,24,500</p>
    </div>
    <div class="report-box">
      <h3>Top Category</h3>
      <p>Traditional</p>
    </div>
    <div class="report-box">
      <h3>Most Viewed Artwork</h3>
      <p>"Buddha Mandala - Gold Edition"</p>
    </div>
  </div>
</main>

<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
