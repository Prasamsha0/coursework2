package com.adg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.adg.model.ArtworkModel;
import com.adg.service.CrudService;

/**
 * Servlet implementation class ArtDetailsController
 */
@WebServlet("/ArtDetailsController")
public class ArtDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CrudService crudService = new CrudService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String artworkIdParam = request.getParameter("id");
            System.out.println("Received request for artwork ID: " + artworkIdParam);
            
            if (artworkIdParam == null || artworkIdParam.isEmpty()) {
                response.sendError(400, "Artwork ID is missing.");
                return;
            }

            int artworkId = Integer.parseInt(artworkIdParam);
            ArtworkModel artwork = crudService.getArtworkById(artworkId);
            System.out.println("Retrieved artwork: " + artwork);
            
            if (artwork == null) {
                response.sendError(404, "Artwork not found.");
                return;
            }

            request.setAttribute("art", artwork);
            request.getRequestDispatcher("/WEB-INF/pages/artdetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(400, "Invalid Artwork ID format.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error occurred.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Unexpected server error: " + e.getMessage());
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
