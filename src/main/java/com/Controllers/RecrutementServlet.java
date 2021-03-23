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
            return;
        }

        try {
            // recrutement
            RecrutementService.addRecrutement(recruteurId, candidatId, offreId);

            request.setAttribute("successMessage", "Recrutement added successfully");
            getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
