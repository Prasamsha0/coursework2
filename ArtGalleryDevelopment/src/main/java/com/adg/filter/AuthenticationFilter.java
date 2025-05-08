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

        // Allow access to login, logout, register, public static assets
        if (path.contains("/login") || path.contains("/logout") || path.contains("/register")
                || path.contains(".css") || path.contains(".js") || path.contains(".png") || path.contains(".jpg")
                || path.contains("/access-denied.jsp")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Session and role check
        Object username = SessionUtil.getAttribute(req, "username");
        Cookie roleCookie = CookieUtil.getCookie(req, "role");
        String role = (roleCookie != null) ? roleCookie.getValue() : null;

        // Not logged in — redirect to login
        if (username == null || role == null) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // Admin-only pages
        if (path.contains("/dashboard") || path.contains("/ManageArtwork") || path.contains("/artlist") || path.contains("/userlist")) {
            if (!"admin".equalsIgnoreCase(role)) {
                req.getRequestDispatcher("/WEB-INF/pages/access-denied.jsp").forward(req, resp);
                return;
            }
        }

        // User-only pages
        if (path.contains("/home") || path.contains("/userUpdate")) {
            if (!"user".equalsIgnoreCase(role)) {
                req.getRequestDispatcher("/WEB-INF/pages/access-denied.jsp").forward(req, resp);
                return;
            }
        }

        // All checks passed
        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
