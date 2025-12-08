package org.example.framgiabookingtours.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.framgiabookingtours.dto.response.AdminDashboardStatsDTO;
import org.example.framgiabookingtours.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final DashboardService dashboardService;

    // @GetMapping
    // public String viewDashboard(Model model) {
    //     return "admin/dashboard";
    // }

    // --- TEST JSON ---
    @GetMapping("/test-api")
    @ResponseBody
    public ResponseEntity<AdminDashboardStatsDTO> getDashboardStatsAPI() {
        AdminDashboardStatsDTO stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }
}