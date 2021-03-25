package com.Controllers;

import com.Services.CandidatService;
import com.Services.RecruteurService;
import com.models.Candidat;
import com.models.Recruteur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CandidatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Candidat> candidats = CandidatService.getListCandidat();

            request.setAttribute("title", "Voir tous les candidats");
            request.setAttribute("component", "browse-candidates");
            request.setAttribute("candidats", candidats);
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
