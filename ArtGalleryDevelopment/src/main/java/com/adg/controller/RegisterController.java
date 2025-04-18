package com.adg.controller;
import com.adg.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import com.adg.service.*;
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
			        request.getParameter("userName"),
			        request.getParameter("contact"),
			        request.getParameter("address"),
			        request.getParameter("email"),
			        request.getParameter("password"),
			        Integer.parseInt(request.getParameter("age")),
			        request.getParameter("gender"),
			        request.getParameter("role"),
			        Date.valueOf(request.getParameter("dob"))
			    );
		 	System.out.println("Dob value: " + request.getParameter("Dob"));


	        RegisterService dao = new RegisterService();
	        boolean inserted=true;
			try {
				inserted = dao.insert(user);
				System.out.print(inserted);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/pages/success.jsp").forward(request, response);

	        
	    
	}
		
	}


