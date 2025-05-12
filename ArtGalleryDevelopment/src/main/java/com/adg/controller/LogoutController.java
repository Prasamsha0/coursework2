package com.adg.controller;

import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * LogoutController handles user logout requests.
 * It clears session data and authentication cookies, then redirects to the login page.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * Shared method that performs the logout process:
     * 1. Ends the user's session.
     * 2. Deletes the "role" cookie (if used for access control).
     * 3. Redirects to the login page.
     *
     * @param request  HTTP request from the client
     * @param response HTTP response to the client
     * @throws IOException if redirect fails
     */
    private void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // End user session and clear any session data
        SessionUtil.invalidateSession(request);

        // Remove "role" cookie to clear stored login role
        CookieUtil.deleteCookie(request, response, "role");

        // Send user back to the login page
        response.sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Handles HTTP GET requests to "/logout".
     * Typically triggered when user clicks a logout link.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processLogout(request, response);
    }

    /**
     * Handles HTTP POST requests to "/logout".
     * Supports logout via form submission or script.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processLogout(request, response);
    }
}
