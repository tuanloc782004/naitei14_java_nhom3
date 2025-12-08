package org.example.framgiabookingtours.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AdminDashboardStatsDTO {
	// 1. Doanh thu
	private BigDecimal totalRevenue;
	private Double revenueGrowth; // % Tăng trưởng so với tháng trước

	// 2. Booking hôm nay
	private Long todayBookings;
	private Long yesterdayBookings; // Để so sánh (tùy chọn hiển thị)

	// 3. User mới (7 ngày qua)
	private Long newUsers7Days;
	private Double userGrowth; // % Tăng trưởng

	// 4. Tour
	private Long totalTours;
	private Long activeTours;
}