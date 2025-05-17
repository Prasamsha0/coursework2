package com.adg.filter;

import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Authentication and Authorization filter.
 * Applies to all incoming requests and enforces session and role-based access.
 */
@WebFilter("/*") // This filter applies to all URL patterns
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Allow open access to login, logout, registration,  and access-denied page
        if (path.contains("/login") || path.contains("/logout") || path.contains("/register")
                || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".png") || path.endsWith(".jpg")
                || path.contains("/access-denied.jsp")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Check if user is logged in and retrieve their role
        Object username = SessionUtil.getAttribute(req, "username");
        Cookie roleCookie = CookieUtil.getCookie(req, "role");
        String role = (roleCookie != null) ? roleCookie.getValue() : null;

        // If no valid session or role, redirect to login page
        if (username == null || role == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // Restrict access to admin-only pages
        if (path.contains("/dashboard") || path.contains("/ManageArtwork") || path.contains("/artlist")) {
            if (!"admin".equalsIgnoreCase(role)) {
                // If not an admin, forward to access-denied page
                req.getRequestDispatcher("/WEB-INF/pages/access-denied.jsp").forward(req, resp);
                return;
            }
        }

        // Restrict access to user-only pages
        if (path.contains("/home") || path.contains("/userUpdate")
                || path.contains("/AboutController") || path.contains("/ContactController")|| path.contains("/Event")) {
            if (!"user".equalsIgnoreCase(role)) {
                // If not a user, forward to access-denied page
                req.getRequestDispatcher("/WEB-INF/pages/access-denied.jsp").forward(req, resp);
                return;
            }
        }

        // All access checks passed; proceed to the next filter or servlet
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // No initialization logic needed
    }

    @Override
    public void destroy() {
        // No cleanup logic needed
    }
}
