package com.Controllers;

import com.Services.RecrutementService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecrutementServlet", value = "/RecrutementServlet")
public class RecrutementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer recruteurId = Integer.parseInt(request.getParameter("idRecruteur"));
        Integer candidatId = Integer.parseInt(request.getParameter("idCandidat"));
        Integer offreId = Integer.parseInt(request.getParameter("offreId"));

        if (recruteurId == null || candidatId == null) {
            request.setAttribute("title", "Home");
            request.setAttribute("component", "index");
            request.setAttribute("errorMessage", "You are not logged in!");
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            return;
        }

        try {
            // recrutement
            RecrutementService.addRecrutement(recruteurId, candidatId, offreId);

            request.setAttribute("title", "Dashboard");
            request.setAttribute("component", "dashboardRecruteur");
            request.setAttribute("successMessage", "Recrutement added successfully");
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("title", "Dashboard");
            request.setAttribute("component", "dashboardRecruteur");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
