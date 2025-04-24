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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserModel userModel = new UserModel(username, password);
		Boolean loginStatus = loginService.loginUser(userModel);

		if (loginStatus != null && loginStatus) {
			SessionUtil.setAttribute(req, "username", username);
			if (username.equals("admin")) {
				CookieUtil.addCookie(resp, "role", "admin", 5 * 30);
				resp.sendRedirect(req.getContextPath() + "/dashboard"); // Redirect to /home
			} else {
				CookieUtil.addCookie(resp, "role", "student", 5 * 30);
				resp.sendRedirect(req.getContextPath() + "/home"); // Redirect to /home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
	}

}
