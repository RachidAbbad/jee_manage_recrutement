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
            if (PostulationService.ajouterPostulation(candidatId, offreId, body))
                rederection("Your Apply has been saved successfully",false,request,response);
            else
                rederection("You can't apply to this job beacause the offer is closed",true,request,response);




        } catch (Exception exception) {
            exception.printStackTrace();
            rederection(exception.getMessage(),true,request,response);
            return;
        }




    }
    private void rederection(String msg,boolean iserror,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {


            if(iserror)
                request.setAttribute("errorMessage",msg);
            else
                request.setAttribute("successMessage",msg);
            List<Offre> offreList = OffreService.getListOffres();

            request.setAttribute("title", "Voir tous les offres");
            request.setAttribute("component", "browse-job");
            request.setAttribute("listOffres", offreList);
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
