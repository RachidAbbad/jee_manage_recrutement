package com.Controllers;

import com.Services.CandidatService;
import com.Services.CvService;
import com.Utils.AppContext;
import com.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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

        try {
            Cv cv = CandidatService.hasCv(candidatId);

            if (cv != null) {
                if (request.getParameter("delete") != null) {
                    // delete cv
                    CvService.deleteCvById(cv.getId());
                } else {
                    // get formations
                    List<Formation> formations = CvService.getFormationsByCvId(cv.getId());
                    List<Experience> experiences = CvService.getExperiencesByCvId(cv.getId());
                    List<Projet> projets = CvService.getProjetsByCvId(cv.getId());
                    List<Competence> competences = CvService.getCompetencesByCvId(cv.getId());

                    request.setAttribute("title", "Update cv");
                    request.setAttribute("component", "submit-resume");
                    request.setAttribute("cv", cv);

                    request.setAttribute("formations", formations);
                    request.setAttribute("experiences", experiences);
                    request.setAttribute("projets", projets);
                    request.setAttribute("competences", competences);

                    getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("title", "Ajouter cv");
                request.setAttribute("component", "submit-resume");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            // request.setAttribute("errorMessage", exception.getMessage());
            // getServletContext().getRequestDispatcher("/ajouter-cv").forward(request, response);
        }
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
            if (CandidatService.hasCv(candidatId) != null) {
                CandidatService.updateCv(candidatId, description, formationsInput, experiencesInput, projetsInput, competencesInput);
                return;
            } else {
                CandidatService.ajouterCv(candidatId, description, formationsInput, experiencesInput, projetsInput, competencesInput);
                return;
                // request.setAttribute("successMessage", "CV is created successfully");
                // getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            // request.setAttribute("errorMessage", exception.getMessage());
            // getServletContext().getRequestDispatcher("/ajouter-cv").forward(request, response);
        }
    }
}
