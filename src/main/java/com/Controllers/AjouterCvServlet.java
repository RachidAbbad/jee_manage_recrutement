package com.Controllers;

import com.Services.CandidatService;
import com.Utils.AppContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjouterCvServlet", value = "/AjouterCvServlet")
public class AjouterCvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compteId = AppContext.isAthorized(request);
        int candidatId = CandidatService.isCandidat(compteId);

        if (candidatId == -1) {
            request.setAttribute("errorMessage", "No permission");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        request.setAttribute("title", "Ajouter cv");
        request.setAttribute("component", "submit-resume");
        getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compteId = AppContext.isAthorized(request);
        int candidatId = CandidatService.isCandidat(compteId);

        if (compteId == -1 || candidatId == -1) {
            return;
        }

        // get information
        String description = request.getParameter("desc");
        String formationsInput = request.getParameter("formationsInput");
        String experiencesInput = request.getParameter("experiencesInput");
        String projetsInput = request.getParameter("projetsInput");
        String competencesInput = request.getParameter("competencesInput");

        try {
            if (CandidatService.hasCv(candidatId)) return;

            CandidatService.ajouterCv(candidatId, description, formationsInput, experiencesInput, projetsInput, competencesInput);
            return;
            // request.setAttribute("successMessage", "CV is created successfully");
            // getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            // request.setAttribute("errorMessage", exception.getMessage());
            // getServletContext().getRequestDispatcher("/ajouter-cv").forward(request, response);
        }
    }
}
