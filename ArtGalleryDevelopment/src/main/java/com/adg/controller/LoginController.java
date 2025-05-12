package com.adg.controller;

import com.adg.model.UserModel;
import com.adg.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.adg.util.CookieUtil;
import com.adg.util.SessionUtil;

/**
 * Servlet implementation class LoginController.
 * Handles login requests, processes user authentication, and sets appropriate session and cookies.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/login", "/"})
public class LoginController extends HttpServlet {
    
    // Serial version UID for version control
    private static final long serialVersionUID = 1L;

    // Instance of the LoginService to handle authentication logic
    private final LoginService loginService;

    /**
     * Default constructor initializes the loginService.
     */
    public LoginController() {
        super();
        this.loginService = new LoginService();  // Initialize LoginService
    }

    /**
     * Handles GET requests to show the login page.
     * 
     * @param request The HttpServletRequest object containing request data
     * @param response The HttpServletResponse object used to send the response
     * @throws ServletException If an error occurs during request processing
     * @throws IOException If an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login.jsp page to display the login form
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for login functionality.
     * Authenticates the user and sets session attributes or handles login failure.
     * 
     * @param req The HttpServletRequest object containing the request data
     * @param resp The HttpServletResponse object used to send the response
     * @throws ServletException If an error occurs during request processing
     * @throws IOException If an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Extract username and password from request parameters
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            // Create a UserModel object with the provided username and password
            UserModel userModel = new UserModel(username, password);

            // Call loginService to validate the user credentials
            Boolean loginStatus = loginService.loginUser(userModel);

            if (loginStatus != null && loginStatus) {
                // Successful login: Set session attributes and handle cookie management
                SessionUtil.setAttribute(req, "username", username);  // Store username in session

                // Set a role-based cookie and redirect the user to the appropriate dashboard or home page
                if ("Admin".equalsIgnoreCase(username)) {
                    CookieUtil.addCookie(resp, "role", "admin", 5 * 300);  // Set admin role cookie
                    resp.sendRedirect(req.getContextPath() + "/dashboard");  // Redirect to admin dashboard
                } else {
                    CookieUtil.addCookie(resp, "role", "user", 5 * 300);  // Set user role cookie
                    resp.sendRedirect(req.getContextPath() + "/home");  // Redirect to user home page
                }
            } else {
                // Login failed: Handle the failure and redirect back to the login page
                handleLoginFailure(req, resp, loginStatus);
            }
        } catch (Exception e) {
            // Exception handling: Log and show error to the user
            req.setAttribute("error", "An unexpected error occurred");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    /**
     * Handles login failures by setting error attributes and forwarding to the login page.
     * 
     * @param req The HttpServletRequest object containing request data
     * @param resp The HttpServletResponse object used to send the response
     * @param loginStatus The status of the login attempt (null if server error, false if invalid credentials)
     * @throws ServletException If an error occurs during request processing
     * @throws IOException If an input/output error occurs
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        // Clear any existing response data before sending a new response
        resp.resetBuffer();

        // Set a meaningful error message based on the login status
        String errorMessage = (loginStatus == null)
            ? "Our server is under maintenance. Please try again later!"
            : "User credential mismatch. Please try again!";

        // Set error message attribute and forward the request back to the login page
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
