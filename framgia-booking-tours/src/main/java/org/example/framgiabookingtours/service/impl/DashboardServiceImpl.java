package org.example.framgiabookingtours.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.example.framgiabookingtours.dto.response.AdminDashboardStatsDTO;
import org.example.framgiabookingtours.enums.TourStatus;
import org.example.framgiabookingtours.repository.BookingRepository;
import org.example.framgiabookingtours.repository.TourRepository;
import org.example.framgiabookingtours.repository.UserRepository;
import org.example.framgiabookingtours.service.DashboardService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    @Override
    public AdminDashboardStatsDTO getDashboardStats() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfToday = now.toLocalDate().atStartOfDay();
        LocalDateTime startOf7DaysAgo = now.minusDays(7);

        // 1. Tính Doanh thu
        BigDecimal totalRevenue = bookingRepository.sumTotalRevenue();
        // (Logic tính % tăng trưởng tạm thời để 0 hoặc random cho đẹp giao diện trước, làm kỹ sau)
        double revenueGrowth = 12.5; 

        // 2. Booking hôm nay
        long todayBookings = bookingRepository.countByBookingDateBetween(startOfToday, now);

        // 3. User mới 7 ngày
        long newUsers = userRepository.countByCreatedAtAfter(startOf7DaysAgo);

        // 4. Tour
        long totalTours = tourRepository.count();
        long activeTours = tourRepository.countByStatus(TourStatus.AVAILABLE);

        return AdminDashboardStatsDTO.builder()
                .totalRevenue(totalRevenue)
                .revenueGrowth(revenueGrowth)
                .todayBookings(todayBookings)
                .newUsers7Days(newUsers)
                .userGrowth(5.2) // Mock tạm
                .totalTours(totalTours)
                .activeTours(activeTours)
                .build();
    }
}