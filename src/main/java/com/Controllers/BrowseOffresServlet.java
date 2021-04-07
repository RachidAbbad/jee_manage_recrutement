package com.Controllers;

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
