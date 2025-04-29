package com.adg.filter;

import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*") // Applies to all requests
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI();
        String contextPath = req.getContextPath();

        // Allow access to login, logout, register, static resources
        if (path.contains("/login") || path.contains("/logout") || path.contains("/register")
                || path.contains(".css") || path.contains(".js") || path.contains(".png") || path.contains(".jpg")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Get session and role
        Object username = SessionUtil.getAttribute(req, "username");
        Cookie roleCookie = CookieUtil.getCookie(req, "role");
        String role = (roleCookie != null) ? roleCookie.getValue() : null;

        // If not logged in, redirect to login
        if (username == null || role == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // Role-based access control
        if (path.contains("/dashboard") && !"admin".equalsIgnoreCase(role)) {
            resp.sendRedirect(contextPath + "/home");
            return;
        }
     // Fix this block:
        if (path.contains("/userUpdate") && !"user".equalsIgnoreCase(role)) {
            resp.sendRedirect(contextPath + "/dashboard");
            return;
        }

        if ((path.contains("/home") || path.contains("/userUpdate")) && !"user".equalsIgnoreCase(role)) {
            resp.sendRedirect(contextPath + "/dashboard");
            return;
        }

        // If everything is valid, continue
        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
