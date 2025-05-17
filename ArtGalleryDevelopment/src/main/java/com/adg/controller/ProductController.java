package com.adg.controller;

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
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductController" })
public class ProductController extends HttpServlet {
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


        // Load and display all artworks
        try {
            List<ArtworkModel> artworks = crudService.getAllArtworks();
            request.setAttribute("artworks", artworks);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
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
    	doGet(request, response);
    }
}
