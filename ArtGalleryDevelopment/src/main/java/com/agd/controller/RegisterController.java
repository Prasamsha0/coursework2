package com.agd.controller;
import com.adg.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported =  true, urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserModel user = new UserModel(
	            0,
	            request.getParameter("userID"),
	            request.getParameter("userName"),
	            request.getParameter("contact"),
	            request.getParameter("address"),
	            Date.valueOf(request.getParameter("email")),
	            request.getParameter("password"),
	            request.getParameter("role"),
	            request.getParameter("password"),
	            request.getParameter("gender")
	        );

	        login dao = new login();
	        boolean inserted=true;
			try {
				inserted = dao.insert(user);
				System.out.print(inserted);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        if (inserted) {
	            response.sendRedirect("success.jsp");
	        } else {
	            response.sendRedirect("error.jsp" );
	        }
	    
	}
	}
		
	}

}
