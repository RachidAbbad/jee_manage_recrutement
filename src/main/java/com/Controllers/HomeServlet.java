package com.Controllers;

import com.Services.CandidatService;
import com.Services.DepartementService;
import com.Services.OffreService;
import com.models.Candidat;
import com.models.Departement;
import com.models.Offre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Offre> offreList = OffreService.getListOffres();
            List<Departement> departementList = DepartementService.getListDepartement();
            List<Candidat> candidatList = CandidatService.getListCandidat();

            request.setAttribute("title", "Home");
            request.setAttribute("component", "index");
            request.setAttribute("listOffres", offreList);
            request.setAttribute("listDepartements", departementList);
            request.setAttribute("listCandidats", candidatList);
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
