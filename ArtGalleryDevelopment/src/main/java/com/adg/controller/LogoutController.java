package com.adg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionUtil.invalidateSession(request); // clears session
        CookieUtil.deleteCookie(request, response, "role"); // clears cookie
        response.sendRedirect(request.getContextPath() + "/login"); // go to login page
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processLogout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processLogout(request, response);
    }
}
