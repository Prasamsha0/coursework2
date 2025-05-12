package com.adg.controller.admin;

import com.adg.model.ArtworkModel;
import com.adg.service.CrudService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling listing, updating, and deleting artworks in the admin panel.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/artlist" })
public class ArtlistController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CrudService crudService = new CrudService();

    /**
     * Handles GET requests for:
     * - Deleting artwork when action=delete is passed.
     * - Displaying the artwork list.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String artworkIdParam = request.getParameter("artworkId");

        // Handle deletion if requested
        if ("delete".equalsIgnoreCase(action) && artworkIdParam != null) {
            try {
                int artworkId = Integer.parseInt(artworkIdParam);
                crudService.deleteArtworkById(artworkId);
                response.sendRedirect(request.getContextPath() + "/artlist");
                return;
            } catch (NumberFormatException e) {
                response.sendError(400, "Invalid artwork ID.");
                return;
            }
        }

        // Load and display all artworks
        try {
            List<ArtworkModel> artworks = crudService.getAllArtworks();
            request.setAttribute("artworks", artworks);
            request.getRequestDispatcher("/WEB-INF/pages/admin/artlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Failed to load artworks.");
        }
    }

    /**
     * Handles POST requests for updating artwork details.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract form parameters
            int artworkId = Integer.parseInt(request.getParameter("artworkId"));
            String name = request.getParameter("artworkName");
            String artist = request.getParameter("artistName");
            String date = request.getParameter("artworkDate");
            String medium = request.getParameter("artworkMedium");
            double price = Double.parseDouble(request.getParameter("artworkPrice"));
            String category = request.getParameter("artworkCategory");
            String format = request.getParameter("artworkFormat");

            // Populate artwork model
            ArtworkModel updatedArtwork = new ArtworkModel();
            updatedArtwork.setArtworkId(artworkId);
            updatedArtwork.setArtworkName(name);
            updatedArtwork.setArtistName(artist);
            updatedArtwork.setArtworkDate(java.sql.Date.valueOf(date));
            updatedArtwork.setArtworkMedium(medium);
            updatedArtwork.setArtworkPrice(price);
            updatedArtwork.setArtworkCategory(category);
            updatedArtwork.setArtworkFormat(format);

            // Update artwork in database
            crudService.updateArtwork(updatedArtwork);

            response.sendRedirect(request.getContextPath() + "/artlist");
        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid number format for ID or price.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Failed to update artwork.");
        }
    }
}
