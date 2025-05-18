package com.adg.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.adg.model.UserModel;
import com.adg.service.UpdateService;
import com.adg.util.SessionUtil;
import com.adg.util.PasswordUtil;
import com.adg.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UpdateController handles user profile updates.
 * It allows authenticated users to view and update their personal information.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userUpdate" })
public class UpdateControllerr extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UpdateService updateService = new UpdateService();

    // Handles GET requests to load the update form with current user data
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Check if user is logged in
        String username = (String) SessionUtil.getAttribute(req, "username");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Fetch current user info
        UserModel user = updateService.getUserByUsername(username);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);
        System.out.println("Image: " + user.getImage());
    }

    // Handles POST requests to update user data
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Check login session
        String username = (String) SessionUtil.getAttribute(req, "username");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Get form input values
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String address = req.getParameter("address");
        String dobStr = req.getParameter("dob");
        String ageStr = req.getParameter("age");
        String password = req.getParameter("password");
        String repass = req.getParameter("repass");

        // Input validation
        if (ValidationUtil.isNullOrEmpty(contact) || !ValidationUtil.isValidPhoneNumber(contact)) {
            setErrorAndReturn(req, resp, "Valid contact number (starts with 98, 10 digits) is required.");
            return;
        }

        if (ValidationUtil.isNullOrEmpty(address)) {
            setErrorAndReturn(req, resp, "Address is required.");
            return;
        }

        if (ValidationUtil.isNullOrEmpty(email) || !ValidationUtil.isValidEmail(email)) {
            setErrorAndReturn(req, resp, "Valid email is required.");
            return;
        }

        if (ValidationUtil.isNullOrEmpty(ageStr) || ValidationUtil.isNullOrEmpty(dobStr)) {
            setErrorAndReturn(req, resp, "Age and Date of Birth are required.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            setErrorAndReturn(req, resp, "Age must be a number.");
            return;
        }

        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
            if (!ValidationUtil.isAgeAtLeast16(dob)) {
                setErrorAndReturn(req, resp, "You must be at least 16 years old.");
                return;
            }
        } catch (Exception e) {
            setErrorAndReturn(req, resp, "Invalid Date of Birth format.");
            return;
        }

        // Optional password update validation
        if (!ValidationUtil.isNullOrEmpty(password)) {
            if (!ValidationUtil.isValidPassword(password)) {
                setErrorAndReturn(req, resp, "Password must contain at least 1 uppercase letter, 1 number, 1 symbol, and be at least 8 characters.");
                return;
            }

            if (!ValidationUtil.doPasswordsMatch(password, repass)) {
                setErrorAndReturn(req, resp, "Passwords do not match.");
                return;
            }

            password = PasswordUtil.encrypt(username, password);
        }

        // Construct updated user object
        UserModel user = new UserModel();
        user.setUserName(username);
        user.setEmail(email);
        user.setContact(contact);
        user.setAddress(address);
        user.setDob(Date.valueOf(dob));
        user.setAge(age);
        if (!ValidationUtil.isNullOrEmpty(password)) {
            user.setPassword(password);
        }
     // Fetch the existing user to preserve the image
        UserModel existingUser = updateService.getUserByUsername(username);
        user.setImage(existingUser.getImage()); // preserve the image


        // Perform update
        try {
            boolean success = updateService.updateUser(user);
            if (success) {
                req.setAttribute("successMessage", "Profile updated successfully.");
            } else {
                req.setAttribute("errorMessage", "Failed to update profile. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "An unexpected error occurred during update.");
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);
    }

    // Utility method to handle error response
    private void setErrorAndReturn(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", message);

        // Reload existing user info to repopulate the form
        String username = (String) SessionUtil.getAttribute(req, "username");
        UserModel user = updateService.getUserByUsername(username);
        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/pages/update.jsp").forward(req, resp);
    }
}
