package com.Controllers;

import com.Services.DepartementService;
import com.Services.OffreService;
import com.models.Offre;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "BrowseOffresServlet", value = "/BrowseOffresServlet")
public class BrowseOffresServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Search:
        String action = request.getServletPath();
        if(action.equals("/search")){
            int departementId = Integer.parseInt(request.getParameter("departement_id"));
            String city = request.getParameter("cityName");
            String job = request.getParameter("jobName");
            System.out.println(city+job+departementId);
            try {
                request.setAttribute("listOffres",OffreService.searchOffer(job,city,departementId));
                request.setAttribute("title", "Results of search");
                request.setAttribute("component", "browse-job");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
            return;
        }
        else if(action.equals("/department")){
            int departementId = Integer.parseInt(request.getParameter("id"));
            try {
                request.setAttribute("listOffres",OffreService.searchOfferByDep(departementId));
                request.setAttribute("title", DepartementService.getDepartementById(departementId).getNom());
                request.setAttribute("component", "browse-job");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }

        try {
            List<Offre> offreList = OffreService.getListOffres();

            request.setAttribute("title", "Voir tous les offres");
            request.setAttribute("component", "browse-job");
            request.setAttribute("listOffres", offreList);
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to 500
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void redirectWithMsg(String msg, boolean isError, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!isError)
            request.setAttribute("successMessage",msg);
        else
            request.setAttribute("errorMessage",msg);
        doGet(request,response);
    }
}
