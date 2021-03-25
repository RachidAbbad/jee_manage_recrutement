package com.Controllers;

import com.Services.OffreService;
import com.Services.RecruteurService;
import com.models.Offre;
import com.models.Recruteur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EntrepriseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recruteur> recruteurs = RecruteurService.getListRecruteurs();

            request.setAttribute("title", "Voir tous les recruteurs");
            request.setAttribute("component", "companies");
            request.setAttribute("recruteurs", recruteurs);
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to 500
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
