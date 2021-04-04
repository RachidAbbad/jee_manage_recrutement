package com.Controllers;

import com.Services.*;
import com.Utils.AppContext;
import com.models.Candidat;
import com.models.Offre;
import com.models.Postulation;
import com.models.Recruteur;

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
        int etat=5;
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

            if(AppContext.getTypeCompte(request)==1){
                Candidat c = CandidatService.getCandidatByIdCompte(AppContext.isAthorized(request));
                if(PostulationService.getPostulation(c.getId(),offreId)==0)
                    etat = 1;
                else
                    etat = 3;
            }//Privileges
            else{
                Recruteur r = RecruteurService.getRecruteurByIdCompte(AppContext.isAthorized(request));
                if(OffreService.getOffreById(offreId).getIdRecruteur() == r.getId())
                    etat = 2;
                else
                    etat = 0;
            }
            request.setAttribute("etat",etat);
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
           /* request.setAttribute("title", "Offer Details");
            request.setAttribute("component", "job-detail");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);*/
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
