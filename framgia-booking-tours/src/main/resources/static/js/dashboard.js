// Revenue Chart
const revenueCtx = document.getElementById('revenueChart')?.getContext('2d');
if (revenueCtx) {
    new Chart(revenueCtx, {
        type: 'bar',
        data: {
            labels: revenueLabels,
            datasets: [{
                label: 'Doanh thu (triệu VNĐ)',
                data: revenueData,
                backgroundColor: 'rgba(59, 130, 246, 0.8)',
                borderColor: 'rgba(59, 130, 246, 1)',
                borderWidth: 1,
                borderRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: { legend: { display: false } },
            scales: { y: { beginAtZero: true } }
        }
    });
}

// Booking Status Chart
const bookingCtx = document.getElementById('bookingStatusChart')?.getContext('2d');
if (bookingCtx) {
    new Chart(bookingCtx, {
        type: 'doughnut',
        data: {
            labels: ['Pending', 'Paid', 'Cancelled'],
            datasets: [{
                data: bookingStatusData,
                backgroundColor: ['#eab308', '#10b981', '#ef4444'],
                borderWidth: 2,
                borderColor: '#fff'
            }]
        },
        options: { plugins: { legend: { display: false } } }
    });
}
