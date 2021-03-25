package com.Controllers;

import com.Services.CompteService;
import com.Services.RecruteurService;
import com.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileRecruteurServlet", value = "/ProfileRecruteurServlet")
public class ProfileRecruteurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
            return;
        }

        Integer recruteurId = Integer.parseInt(request.getParameter("id"));

        try {
            // get recruteur
            Recruteur recruteur = RecruteurService.getRecruteurById(recruteurId);

            if (recruteur == null) {
                // redirect to 404
                request.setAttribute("title", "Home");
                request.setAttribute("component", "index");
                request.setAttribute("errorMessage", "No recruteur found");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
                return;
            }

            Compte compte = CompteService.getCompteById(recruteur.getIdCompte());

            // get offres of recruteur
            List<Offre> myOffres = RecruteurService.getOffresOfRecruteur(recruteur.getId());

            request.setAttribute("title", "Recruteur");
            request.setAttribute("component", "profile-recruteur");
            request.setAttribute("recruteur", recruteur);
            request.setAttribute("compte", compte);
            request.setAttribute("listOffres", myOffres);

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
