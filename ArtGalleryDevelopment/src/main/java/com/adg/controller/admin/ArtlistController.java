package com.adg.controller.admin;

import com.adg.service.CrudService;
import com.adg.model.ArtworkModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/artlist" })
public class ArtlistController extends HttpServlet {

    private CrudService crudService = new CrudService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle deletion
        String action = request.getParameter("action");
        String artworkIdParam = request.getParameter("artworkId");

        if ("delete".equals(action) && artworkIdParam != null) {
            int artworkId = Integer.parseInt(artworkIdParam);
            crudService.deleteArtworkById(artworkId);
            response.sendRedirect(request.getContextPath() + "/artlist");
            return;
        }

        // Load artworks
        try {
            List<ArtworkModel> artworks = crudService.getAllArtworks();
            request.setAttribute("artworks", artworks);
            request.getRequestDispatcher("/WEB-INF/pages/admin/artlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Failed to load artworks.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int artworkId = Integer.parseInt(request.getParameter("artworkId"));
            String name = request.getParameter("artworkName");
            String artist = request.getParameter("artistName");
            String date = request.getParameter("artworkDate");
            String medium = request.getParameter("artworkMedium");
            double price = Double.parseDouble(request.getParameter("artworkPrice"));
            String category = request.getParameter("artworkCategory");
            String format = request.getParameter("artworkFormat");

            ArtworkModel updatedArtwork = new ArtworkModel();
            updatedArtwork.setArtworkId(artworkId);
            updatedArtwork.setArtworkName(name);
            updatedArtwork.setArtistName(artist);
            updatedArtwork.setArtworkDate(java.sql.Date.valueOf(date));
            updatedArtwork.setArtworkMedium(medium);
            updatedArtwork.setArtworkPrice(price);
            updatedArtwork.setArtworkCategory(category);
            updatedArtwork.setArtworkFormat(format);

            crudService.updateArtwork(updatedArtwork);

            response.sendRedirect(request.getContextPath() + "/artlist");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Failed to update artwork.");
        }
    }
}
