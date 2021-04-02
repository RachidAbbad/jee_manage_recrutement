package com.Controllers;

import com.Services.*;
import com.Utils.AppContext;
import com.models.Candidat;
import com.models.Offre;
import com.models.Recrutement;
import com.models.Recruteur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostulationServlet", value = "/PostulationServlet")
public class PostulationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compteId = AppContext.isAthorized(request);
        Integer offreId = Integer.parseInt(request.getParameter("offreId"));
        int candidatId = CandidatService.isCandidat(compteId);
        String body = request.getParameter("body");

        if (compteId == -1 || candidatId == -1 || request.getParameter("offreId") == null) {
            request.setAttribute("errorMessage", "No permission");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        try {

            PostulationService.ajouterPostulation(candidatId, offreId, body);
                request.setAttribute("successMessage", "Postulation added successfully");
                response.sendRedirect(request.getHeader("referer"));
            } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("errorMessage", exception.getMessage());
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
