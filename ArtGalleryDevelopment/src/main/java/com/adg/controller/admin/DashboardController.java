package com.adg.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import com.adg.config.DbConfig;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userCount = 0;
        int artworkCount = 0;

        try (Connection conn = DbConfig.getConnection()) {
            PreparedStatement userStmt = conn.prepareStatement("SELECT COUNT(*) FROM user");
            ResultSet userRs = userStmt.executeQuery();
            if (userRs.next()) {
                userCount = userRs.getInt(1);
            }

            PreparedStatement artworkStmt = conn.prepareStatement("SELECT COUNT(*) FROM artwork");
            ResultSet artworkRs = artworkStmt.executeQuery();
            if (artworkRs.next()) {
                artworkCount = artworkRs.getInt(1);
            }

            request.setAttribute("userCount", userCount);
            request.setAttribute("artworkCount", artworkCount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }
}
