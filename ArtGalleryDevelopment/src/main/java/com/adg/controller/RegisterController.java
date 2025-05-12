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
 * RegisterController handles user registration.
 * It validates form data, processes image uploads (if any), and stores new user data.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service class to handle database logic
    private final RegisterService registerService = new RegisterService();

    // Display the registration page (GET request)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    // Handle form submission (POST request)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Validate form data
            String validationMessage = validateRegistrationForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }

            // Create UserModel object from request data
            UserModel userModel = extractUserModel(req);

            // Insert user data into the database
            boolean isAdded = registerService.insert(userModel);

            if (isAdded) {
                handleSuccess(req, resp, "Registration successful!", "/WEB-INF/pages/login.jsp");
            } else {
                handleError(req, resp, "Could not register your account. Please try again later!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // For debugging/logging
            handleError(req, resp, "An unexpected error occurred. Please try again later!");
        }
    }

    /**
     * Validates the registration form input fields.
     * Returns a string with the error message if invalid, or null if all fields are valid.
     */
    private String validateRegistrationForm(HttpServletRequest req) {
        // Extract parameters
        String username = req.getParameter("userName");
        String contact = req.getParameter("contact");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String ageStr = req.getParameter("age");
        String gender = req.getParameter("gender");
        String repass = req.getParameter("repass");
        String dobStr = req.getParameter("dob");

        // Check for missing fields
        if (ValidationUtil.isNullOrEmpty(username)) return "Username is required.";
        if (ValidationUtil.isNullOrEmpty(contact)) return "Contact is required.";
        if (ValidationUtil.isNullOrEmpty(address)) return "Address is required.";
        if (ValidationUtil.isNullOrEmpty(email)) return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(password)) return "Password is required.";
        if (ValidationUtil.isNullOrEmpty(ageStr)) return "Age is required.";
        if (ValidationUtil.isNullOrEmpty(gender)) return "Gender is required.";
        if (ValidationUtil.isNullOrEmpty(repass)) return "Please re-enter the password.";
        if (ValidationUtil.isNullOrEmpty(dobStr)) return "Date of birth is required.";

        // Validate and convert date of birth
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Validate and convert age
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (Exception e) {
            return "Invalid age format.";
        }

        // Field-specific validations
        if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
            return "Username must start with a letter and contain only letters and numbers.";

        if (!ValidationUtil.isValidGender(gender))
            return "Gender must be 'male' or 'female'.";

        if (!ValidationUtil.isValidEmail(email))
            return "Invalid email format.";

        if (!ValidationUtil.isValidPhoneNumber(contact))
            return "Phone number must be 10 digits and start with 98.";

        if (!ValidationUtil.isValidPassword(password))
            return "Password must be at least 8 characters, include an uppercase letter, number, and symbol.";

        if (!ValidationUtil.doPasswordsMatch(password, repass))
            return "Passwords do not match.";

        if (!ValidationUtil.isAgeAtLeast16(dob))
            return "You must be at least 16 years old to register.";

        return null; // No validation errors
    }

    /**
     * Extracts user data from request and creates a UserModel object.
     */
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

        // Encrypt password before saving
        password = PasswordUtil.encrypt(username, password);

        // Create and return the user object
        return new UserModel(0, username, contact, address, email, password, age, gender, repass, dob);
    }

    /**
     * Handles successful registration by showing a success message and redirecting.
     */
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    /**
     * Handles errors by showing error message and preserving form input values.
     */
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);

        // Preserve form input values
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
