package com.adg.controller;

import java.io.IOException;
import java.sql.Date;

import com.adg.model.UserModel;
import com.adg.service.UpdateService;
import com.adg.util.SessionUtil;
import com.adg.service.LoginService;
import com.adg.util.PasswordUtil;
import com.adg.util.ValidationUtil;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            
            // Get user data from database
            UserModel user = new LoginService().getUserByUsername(username);
            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
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
    	getServletContext().log("Inside doPost");

	    HttpSession session = req.getSession();

	    // Check if the user is logged in
	    UserModel currentUser = (UserModel) session.getAttribute("user");
	    if (currentUser == null) {
	        resp.sendRedirect("login");
	        return;
	    }
        // Retrieve user data from request parameters
		String username = req.getParameter("userName");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		String repass = req.getParameter("repass");
		Date dob = Date.valueOf(req.getParameter("dob"));

	    boolean hasError=false;

	    UserModel user = new UserModel(
	    	    currentUser.getUserId(),
	    	    username,
	    	    contact,
	    	    address,
	    	    email,
	    	    password != null && !password.isEmpty() ? password : currentUser.getPassword(),
	    	    age,
	    	    currentUser.getGender(),
	    	    dob
	    	);


	    
	    LoginService dao = new LoginService();
        boolean updated = false;

        try {
            updated = dao.updateUser(user);  // Update user in the database
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updated) {
            // Update session with the updated user data
        	session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/userUpdate");
        } else {
            resp.sendRedirect("error.jsp");
        }
    }
	
}