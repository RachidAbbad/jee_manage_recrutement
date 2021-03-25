package com.Controllers;

import com.Services.*;
import com.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfileCandidatServlet", value = "/ProfileCandidatServlet")
public class ProfileCandidatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
            return;
        }

        Integer candidatId = Integer.parseInt(request.getParameter("id"));

        try {
            // get candidat
            Candidat candidat = CandidatService.getCandidatById(candidatId);

            if (candidat == null) {
                // redirect to 404
                request.setAttribute("title", "Home");
                request.setAttribute("component", "index");
                request.setAttribute("errorMessage", "No candidat found");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
                return;
            }

            Compte compte = CompteService.getCompteById(candidat.getIdCompte());
            Cv cv = CvService.getCvByCandidatId(candidatId);

            request.setAttribute("title", "Candidat");
            request.setAttribute("component", "profile-candidat");
            request.setAttribute("candidat", candidat);
            request.setAttribute("cv", cv);
            request.setAttribute("compte", compte);

            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to 500
            request.setAttribute("title", "Home");
            request.setAttribute("component", "index");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
