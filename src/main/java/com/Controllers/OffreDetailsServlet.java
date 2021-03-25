package com.Controllers;

import com.Services.OffreService;
import com.Services.PostulationService;
import com.models.Offre;
import com.models.Postulation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OffreDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
            return;
        }

        Integer offreId = Integer.parseInt(request.getParameter("id"));

        try {
            // get offre
            Offre offre = OffreService.getOffreById(offreId);

            if (offre == null) {
                // redirect to 404
                response.sendRedirect("/");
                return;
            }

            List<Postulation> mesPostulations = PostulationService.getPostulationsByOffreId(offre.getId());

            request.setAttribute("title", "Home");
            request.setAttribute("component", "job-detail");
            request.setAttribute("offre", offre);
            request.setAttribute("listPostulations", mesPostulations);

            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to 500
            request.setAttribute("title", "Home");
            request.setAttribute("component", "job-detail");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
