<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Mandala Studios</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<jsp:include page="/WEB-INF/pages/admin/header2.jsp" />

<section class="dashboard-container">
    <h1>Admin Dashboard</h1>
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
    <div class="chart-section">
        <canvas id="dataChart"></canvas>
    </div>
</section>
<script>
    const ctx = document.getElementById('dataChart').getContext('2d');
    const chart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Users', 'Artworks'],
            datasets: [{
                label: 'Count',
                data: [<c:out value="${userCount}" />, <c:out value="${artworkCount}" />],
                backgroundColor: ['#4e73df', '#1cc88a'],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            }
        }
    });
</script>
<jsp:include page="/WEB-INF/pages/footer.jsp" />

</body>
</html>
