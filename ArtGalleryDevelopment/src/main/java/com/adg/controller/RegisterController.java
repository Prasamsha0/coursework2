package com.adg.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.adg.model.UserModel;
import com.adg.service.RegisterService;
import com.adg.util.ImageUtil;
import com.adg.util.PasswordUtil;
import com.adg.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * RegisterController handles user registration requests and processes form
 * submissions. It also manages file uploads and account creation.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final RegisterService registerService = new RegisterService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Validate and extract user model
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			UserModel userModel = extractUserModel(req);
			Boolean isAdded = registerService.insert(userModel);

			if (isAdded) {
			    handleSuccess(req, resp, "Registration successful!", "/WEB-INF/pages/login.jsp");
			    return;
			}
	
			else {
				handleError(req, resp, "Could not register your account. Please try again later!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}

	private String validateRegistrationForm(HttpServletRequest req) {
		String username = req.getParameter("userName");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String ageStr = req.getParameter("age");
		String gender = req.getParameter("gender");
		String repass = req.getParameter("repass");
		String dobStr = req.getParameter("dob");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(username))
			return "username is required.";
		if (ValidationUtil.isNullOrEmpty(contact))
			return "contact is required.";
		if (ValidationUtil.isNullOrEmpty(address))
			return "address is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(ageStr))
			return "Age is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(repass))
			return "Re-enter the password.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";;

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		Integer age;
		try {
			age = Integer.parseInt(ageStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(contact))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, repass))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

	

		return null; // All validations passed
	}

	private UserModel extractUserModel(HttpServletRequest req) throws Exception {
		String username = req.getParameter("userName");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		String repass = req.getParameter("repass");
		Date dob = Date.valueOf(req.getParameter("dob"));


		// Assuming password validation is already done in validateRegistrationForm
		password = PasswordUtil.encrypt(username, password);
		return new UserModel(0, username, contact, address, email,password, age, gender, repass , dob);
	}

	

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("username", req.getParameter("userName"));
		req.setAttribute("contact", req.getParameter("contact"));
		req.setAttribute("address", req.getParameter("address"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("password", req.getParameter("password"));
		req.setAttribute("age", req.getParameter("age"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("repass", req.getParameter("repass"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}
}