package com.adg.controller.admin;

import com.adg.model.UserModel;
import com.adg.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for displaying the list of users in the admin panel.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userlist" })
public class UserlistController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserService();

    /**
     * Handles GET requests to load all users and forward to the user list page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<UserModel> users = userService.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/pages/admin/userlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load user list.");
        }
    }

    /**
     * Redirects POST requests to GET logic.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
