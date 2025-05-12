<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard - Mandala Studios</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css?v=1.0">
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />

<section class="dashboard-container">
  <h1>Admin Dashboard</h1>

  <!-- Statistics -->
	  <div class="system-info-card">
	    <h2>System Info</h2>
	    <ul>
	        <li><strong>Logged in as:</strong> ${currentUser}</li>
	        <li><strong>Server Time:</strong> <span id="serverTime"></span></li>
	        <li><strong>Session Started:</strong> <%= new java.text.SimpleDateFormat("hh:mm a").format(session.getCreationTime()) %></li>
	        <li><strong>Session Duration:</strong> <span id="sessionDuration">Calculating...</span></li>
	        <li><strong>Browser:</strong> <script>document.write(navigator.userAgent);</script></li>
	    </ul>
	</div>
	  
  <div class="stats-grid">
    <div class="stat-card">
      <h2>Total Users</h2>
      <p>${userCount}</p>
    </div>
    <div class="stat-card">
      <h2>Total Artworks</h2>
      <p>${artworkCount}</p>
    </div>
  </div>

  <!-- Chart -->
  <div class="chart-wrapper">
    <canvas id="dataChart"></canvas>
  </div>

  <!-- Recent Info -->
  <div class="recent-row">
    <!-- Recent Users -->
    <div class="recent-box">
      <h3>Recent Users</h3>
      <c:forEach var="user" items="${recentUsers}">
        <div class="mini-card">
          <strong>${user.userName}</strong>
          <p><i class="fas fa-envelope"></i> ${user.email}</p>
          <p><i class="fas fa-venus-mars"></i> ${user.gender}</p>
        </div>
      </c:forEach>
    </div>

    <!-- Recent Artworks -->
    <div class="recent-box">
      <h3>Recent Artworks</h3>
      <c:forEach var="art" items="${recentArtworks}">
        <div class="mini-card">
          <strong>${art.artworkName}</strong>
          <p><i class="fas fa-user"></i> ${art.artistName}</p>
          <p><i class="fas fa-dollar-sign"></i> NPR ${art.artworkPrice}</p>
        </div>
      </c:forEach>
    </div>
  </div>
</section>

<script>
  const ctx = document.getElementById('dataChart').getContext('2d');
  const chart = new Chart(ctx, {
    type: 'pie',
    data: {
      labels: ['Users', 'Artworks'],
      datasets: [{
        data: [<c:out value="${userCount}" />, <c:out value="${artworkCount}" />],
        backgroundColor: ['#4e73df', '#1cc88a']
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'bottom' }
      }
    }
  });
</script>
<script>
  // Live Server Time
  function updateServerTime() {
    const now = new Date();
    document.getElementById('serverTime').textContent = now.toLocaleTimeString();
  }

  setInterval(updateServerTime, 1000);
  updateServerTime();

  // Session duration calculation
  const sessionStart = <%= session.getCreationTime() %>;
  function updateSessionDuration() {
    const now = new Date().getTime();
    const diff = now - sessionStart;
    const mins = Math.floor(diff / 60000);
    const secs = Math.floor((diff % 60000) / 1000);
    document.getElementById('sessionDuration').textContent = `${mins}m ${secs}s`;
  }

  setInterval(updateSessionDuration, 1000);
</script>

<jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
