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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check for delete action
        String action = request.getParameter("action");
        String artworkIdParam = request.getParameter("artworkId");

        if ("delete".equals(action) && artworkIdParam != null) {
            int artworkId = Integer.parseInt(artworkIdParam);
            crudService.deleteArtworkById(artworkId);
            response.sendRedirect(request.getContextPath() + "/admin/artworks");
            return;
        }

        // Normal artwork list loading logic
        try {
            List<ArtworkModel> artworks = crudService.getAllArtworks(); // You'd need to have this method in CrudService
            request.setAttribute("artworks", artworks);
            request.getRequestDispatcher("/WEB-INF/pages/admin/artlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Failed to load artworks.");
        }
    }
}
