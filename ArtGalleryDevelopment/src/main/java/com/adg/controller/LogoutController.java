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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Clear all session attributes
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Remove all cookies
        CookieUtil.deleteCookie(req, resp, "role");
        CookieUtil.deleteCookie(req, resp, "JSESSIONID");
        
        // Add cache control headers
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires", "0");
        
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}

