package com.Controllers;

import com.Services.*;
import com.Utils.AppContext;
import com.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "PostulationServlet", value = "/PostulationServlet")
public class PostulationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer offreId = Integer.parseInt(request.getParameter("offreId"));


        int compteId = AppContext.isAthorized(request);
        int candidatId = CandidatService.isCandidat(compteId);
        String body = request.getParameter("body");
        if (compteId == -1 || candidatId == -1 || request.getParameter("offreId") == null) {
            request.setAttribute("errorMessage", "No permission");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        try {
            PostulationService.ajouterPostulation(candidatId, offreId, body);
                request.setAttribute("successMessage", "Your Postulation has added successfully");
                rederection(request,response);
            } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("errorMessage", exception.getMessage());
            request.setAttribute("etat", "2");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }




    }
    private void rederection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

            request.setAttribute("title", "Offer Details");
            request.setAttribute("component", "job-detail");
            request.setAttribute("offre", offre);
            request.setAttribute("listPostulations", mesPostulations);

            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("title", "Offer Details");
            request.setAttribute("component", "job-detail");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }


    }
}
