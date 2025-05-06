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
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported =  true, urlPatterns = {"/login","/"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoginService loginService;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        this.loginService = new LoginService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {
	    try {
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        UserModel userModel = new UserModel(username, password);
	        Boolean loginStatus = loginService.loginUser(userModel);
	        if (loginStatus != null && loginStatus) {
	            SessionUtil.setAttribute(req, "username", username);

	            // Overwrite the cookie every time
	            if ("Admin".equalsIgnoreCase(username)) {
	                CookieUtil.addCookie(resp, "role", "admin", 5 * 300);
	                resp.sendRedirect(req.getContextPath() + "/dashboard");
	            } else {
	                CookieUtil.addCookie(resp, "role", "user", 5 * 300);
	                resp.sendRedirect(req.getContextPath() + "/home");
	            }
	        }
	            else {
	            handleLoginFailure(req, resp, loginStatus);
	        }
	    } catch (Exception e) {
	        req.setAttribute("error", "An unexpected error occurred");
	        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	    }
	}
	
	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
	        throws ServletException, IOException {
	    // Clear any existing response data
	    resp.resetBuffer();
	    
	    String errorMessage = (loginStatus == null) 
	        ? "Our server is under maintenance. Please try again later!"
	        : "User credential mismatch. Please try again!";
	        
	    req.setAttribute("error", errorMessage);
	    req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	}
	


}