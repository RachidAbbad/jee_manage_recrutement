package com.Controllers;

import com.Services.CandidatService;
import com.Services.DepartementService;
import com.Services.OffreService;
import com.Utils.AppContext;
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
        String action = request.getServletPath();
        if(action.equals("/search")){
            int departementId = Integer.parseInt(request.getParameter("departement_id"));
            String city = request.getParameter("cityName");
            String job = request.getParameter("jobName");
            try {
                request.setAttribute("listOffres",OffreService.searchOffer(job,city,departementId));
                request.setAttribute("title", "Results of search");
                request.setAttribute("component", "browse-job");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }
        else if(action.equals("/department")){
            int departementId = Integer.parseInt(request.getParameter("id"));
            try {
                request.setAttribute("listOffres",OffreService.searchOfferByDep(departementId));
                request.setAttribute("title",DepartementService.getDepartementById(departementId).getNom());
                request.setAttribute("component", "browse-job");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }


        try {
            List<Departement> departementList = DepartementService.getListDepartement();
            List<Candidat> candidatList = CandidatService.getListCandidat();
            List<Offre> offreList = OffreService.getListOffres();
            request.setAttribute("title", "Home");
            request.setAttribute("component", "index");
            request.setAttribute("listOffres", offreList);
            request.setAttribute("listDepartements", departementList);
            request.setAttribute("listCandidats", candidatList);
            request.setAttribute("etat", AppContext.getTypeCompte(request));
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
