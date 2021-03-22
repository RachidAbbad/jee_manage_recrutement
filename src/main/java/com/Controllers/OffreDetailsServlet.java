package com.Controllers;

import com.Services.OffreService;
import com.models.Offre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OffreDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer offreId = Integer.parseInt(request.getParameter("id"));

        if (offreId == null) {
            response.sendRedirect("/");
            return;
        }

        try {
            // get offre
            Offre offre = OffreService.getOffreById(offreId);

            if (offre == null) {
                // redirect to 404
                return;
            }

            request.setAttribute("title", "Home");
            request.setAttribute("component", "job-detail");
            request.setAttribute("offre", offre);
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
