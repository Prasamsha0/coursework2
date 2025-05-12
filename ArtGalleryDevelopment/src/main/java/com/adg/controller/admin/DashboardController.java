package com.adg.controller.admin;

import com.adg.model.ArtworkModel;
import com.adg.model.UserModel;
import com.adg.service.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for admin dashboard displaying summary statistics
 * and recent users and artworks.
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DashboardService dashboardService = new DashboardService();

    /**
     * Handles GET requests to load dashboard data.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve statistics and recent entries
            int userCount = dashboardService.getUserCount();
            int artworkCount = dashboardService.getArtworkCount();
            List<UserModel> recentUsers = dashboardService.getRecentUsers(5);
            List<ArtworkModel> recentArtworks = dashboardService.getRecentArtworks(5);

            // Set data into request scope
            request.setAttribute("userCount", userCount);
            request.setAttribute("artworkCount", artworkCount);
            request.setAttribute("recentUsers", recentUsers);
            request.setAttribute("recentArtworks", recentArtworks);

            // Forward to JSP
            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dashboard data loading failed.");
        }
    }
}
