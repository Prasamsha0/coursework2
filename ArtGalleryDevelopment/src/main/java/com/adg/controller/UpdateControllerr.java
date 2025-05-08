package com.adg.controller;

import java.io.IOException;
import java.sql.Date;

import com.adg.model.UserModel;
import com.adg.service.UpdateService;
import com.adg.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.adg.util.PasswordUtil;
import com.adg.util.ValidationUtil;

/**
 * Servlet implementation for handling user update operations.
 * 
 * This servlet processes HTTP requests for updating user information.
 * It interacts with the UpdateService to perform database operations and 
 * forwards requests to the appropriate JSP page for user interaction.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userUpdate" })
public class UpdateControllerr extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Service for updating user information

    /**
     * Default constructor initializes the UpdateService instance.
     */
    public UpdateControllerr() {
        super();
    }

    /**
     * Handles HTTP GET requests by retrieving user information from the session 
     * and forwarding the request to the update JSP page.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

            
            // Check session using the same attribute name as login
            String username = (String) SessionUtil.getAttribute(req, "username");
            if (username == null) {
                resp.sendRedirect(req.getContextPath() + "/userUpdate");
                return;
            }
            
            // Get user data from database
            UserModel user = new UpdateService().getUserByUsername(username);
            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/userUpdate");
                return;
            }
            
            // Set attributes for JSP
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);
        }
    
    

    /**
     * Handles HTTP POST requests for updating user information.
     * Retrieves user data from the request parameters, updates the user record 
     * in the database using UpdateService, and redirects to the dashboard or 
     * handles update failure.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String username = (String) SessionUtil.getAttribute(req, "username");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Get form parameters
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String address = req.getParameter("address");
        String dobStr = req.getParameter("dob");
        String ageStr = req.getParameter("age");

        try {
            int age = Integer.parseInt(ageStr);
            Date dob = Date.valueOf(dobStr); // Must be yyyy-MM-dd format

            // Set updated values
            UserModel user = new UserModel();
            user.setUserName(username);
            user.setEmail(email);
            user.setContact(contact);
            user.setAddress(address);
            user.setDob(dob);
            user.setAge(age);

            boolean success = new UpdateService().updateUser(user);

            if (success) {
                req.setAttribute("successMessage", "Profile updated successfully.");
            } else {
                req.setAttribute("errorMessage", "Failed to update profile.");
            }

            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Invalid input data.");
            req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);
        }
        System.out.println("New name: " + name);

    }
    
    

	
}