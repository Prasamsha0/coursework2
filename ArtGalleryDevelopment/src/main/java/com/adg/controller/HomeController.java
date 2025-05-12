package com.adg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeController handles requests to the "/home" URL.
 * It forwards users to the home page JSP view.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Default constructor
    public HomeController() {
        super();
        // No additional initialization needed here
    }

	/**
	 * Handles GET requests to "/home".
	 * Forwards the request to the home.jsp page (located in WEB-INF/pages).
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		// Forward the request to the JSP file for rendering the home page
		request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to "/home".
	 * For simplicity, redirects POST to the same logic as GET.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		// Reuse the GET logic for POST requests
		doGet(request, response);
	}
}
