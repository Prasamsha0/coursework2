package com.adg.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String DASHBOARD = "/dashboard";
    private static final String USER_UPDATE = "/userUpdate"; // Fixed single underscore

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        
        // Allow access to resources
        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }
        
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String userRole = CookieUtil.getCookie(req, "role") != null ? 
                         CookieUtil.getCookie(req, "role").getValue() : null;

        if ("admin".equals(userRole)) {
            // Admin access rules
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            } else {
                chain.doFilter(request, response);
            }
        } else if ("user".equals(userRole)) {
            // User access rules
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
                res.sendRedirect(req.getContextPath() + HOME);
            } else if (uri.contains(USER_UPDATE) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + HOME);
            }
        } else {
            // Guest access rules
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || 
                uri.endsWith(HOME) || uri.endsWith(ROOT)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN);
            }
        }
        System.out.println("=== FILTER DEBUG ===");
        System.out.println("URI: " + uri);
        System.out.println("Session username: " + SessionUtil.getAttribute(req, "username"));
        System.out.println("Cookie role: " + userRole);
        System.out.println("isLoggedIn: " + isLoggedIn);
    }

	

	@Override
	public void destroy() {
		// Cleanup logic, if required
	}
}