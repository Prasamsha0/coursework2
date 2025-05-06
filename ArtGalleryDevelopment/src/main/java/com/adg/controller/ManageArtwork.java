package com.adg.controller;

import com.adg.model.ArtworkModel;
import com.adg.service.CrudService;
import com.adg.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(asyncSupported = true, urlPatterns = {"/ManageArtwork"})
public class ManageArtwork extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManageArtwork() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/admin/manageartwork.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Validate input
            String validationMessage = validateArtworkForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }

            // Extract artwork model
            ArtworkModel artwork = extractArtworkModel(req);
            CrudService service = new CrudService();
            boolean isAdded = service.insert(artwork);

            if (isAdded) {
                handleSuccess(req, resp, "Artwork added successfully!", "/WEB-INF/pages/admin/manageartwork.jsp");
            } else {
                handleError(req, resp, "Could not add artwork. Please try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            handleError(req, resp, "An unexpected error occurred.");
        }
    }

    private String validateArtworkForm(HttpServletRequest req) {
        String name = req.getParameter("artworkName");
        String artist = req.getParameter("artistName");
        String dateStr = req.getParameter("artworkDate");
        String medium = req.getParameter("artworkMedium");
        String priceStr = req.getParameter("artworkPrice");
        String category = req.getParameter("artworkCategory");
        String format = req.getParameter("artworkFormat");
        System.out.println(name);
        System.out.println(artist);
        System.out.println(dateStr);
        System.out.println(priceStr);
        if (ValidationUtil.isNullOrEmpty(name)) return "Artwork name is required.";
        if (ValidationUtil.isNullOrEmpty(artist)) return "Artist name is required.";
        if (ValidationUtil.isNullOrEmpty(dateStr)) return "Artwork date is required.";
        if (ValidationUtil.isNullOrEmpty(medium)) return "Artwork medium is required.";
        if (ValidationUtil.isNullOrEmpty(priceStr)) return "Artwork price is required.";
        if (ValidationUtil.isNullOrEmpty(category)) return "Artwork category is required.";
        if (ValidationUtil.isNullOrEmpty(format)) return "Artwork format is required.";


		if (!ValidationUtil.isValidArtworkName(name)) return "Invalid artwork name.";
		if (!ValidationUtil.isValidArtistName(artist)) return "Invalid artist name.";
		if (!ValidationUtil.isValidArtworkMedium(medium)) return "Medium is required.";
		if (!ValidationUtil.isValidArtworkCategory(category)) return "Invalid artwork category.";
		if (!ValidationUtil.isValidArtworkFormat(format)) return "Invalid artwork format.";

		
		double price;
		try {
		    price = Double.parseDouble(priceStr);
		    if (!ValidationUtil.isValidArtworkPrice(price)) return "Price must be greater than 0.";
		} catch (NumberFormatException e) {
		    return "Price must be a valid number.";
		}
		
		LocalDate date;
		try {
		    date = LocalDate.parse(dateStr);
		    if (!ValidationUtil.isValidArtworkDate(date)) return "Artwork date cannot be in the future.";
		} catch (Exception e) {
		    return "Invalid artwork date.";
		}
        return null;

    }

    private ArtworkModel extractArtworkModel(HttpServletRequest req) {
        int id = 0; // default for new records
        String name = req.getParameter("artworkName");
        String artist = req.getParameter("artistName");
        Date date = Date.valueOf(req.getParameter("artworkDate"));
        String medium = req.getParameter("artworkMedium");
        double price = Double.parseDouble(req.getParameter("artworkPrice"));
        String category = req.getParameter("artworkCategory");
        String format = req.getParameter("artworkFormat");

        return new ArtworkModel(0, name, artist, date, medium, price, category, format);
    }
   
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String error)
            throws ServletException, IOException {
        req.setAttribute("error", error);

        // Preserve form data
        req.setAttribute("artworkName", req.getParameter("artworkName"));
        req.setAttribute("artistName", req.getParameter("artistName"));
        req.setAttribute("artworkDate", req.getParameter("artworkDate"));
        req.setAttribute("artworkMedium", req.getParameter("artworkMedium"));
        req.setAttribute("artworkPrice", req.getParameter("artworkPrice"));
        req.setAttribute("artworkCategory", req.getParameter("artworkCategory"));
        req.setAttribute("artworkFormat", req.getParameter("artworkFormat"));

        req.getRequestDispatcher("/WEB-INF/pages/admin/manageartwork.jsp").forward(req, resp);
    }
}
