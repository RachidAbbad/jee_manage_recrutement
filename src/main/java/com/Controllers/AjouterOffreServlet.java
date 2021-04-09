package com.Controllers;

import com.Services.DepartementService;
import com.Services.OffreService;
import com.Utils.AppContext;
import com.models.Departement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AjouterOffreServlet", value = "/AjouterOffreServlet")
public class AjouterOffreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(AppContext.getTypeCompte(request)==1){
                response.sendRedirect("/permissionDenied");
                return;
            }

            List<Departement> departements = DepartementService.getListDepartement();
            request.setAttribute("title", "Ajouter offre");
            request.setAttribute("component", "ajouter-offre");
            request.setAttribute("listDepartements", departements);
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);

        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to 500
            request.setAttribute("title", "Ajouter offre");
            request.setAttribute("component", "ajouter-offre");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compteId = AppContext.isAthorized(request);
        if (compteId == -1) {
            response.sendRedirect("/logout");
            return;
        }

        String titre = request.getParameter("offre_titre");
        String desc = request.getParameter("offre_desc");
        String emplacement = request.getParameter("offre_emplacement");
        String typeContrat = request.getParameter("offre_type_contrat");
        String metier = request.getParameter("offre_metier");
        String salairePrime = request.getParameter("offre_salaire");
        String competences = request.getParameter("offre_competences");
        int departementId = Integer.parseInt(request.getParameter("departement_id"));

        try {
            OffreService.ajouterOffre(titre, desc, emplacement, typeContrat, metier, salairePrime, competences, departementId, compteId);
            request.setAttribute("successMessage", "Your job offer has been saved successfully");
            doGet(request,response);
        } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("errorMessage", "Your job has not been saved because an internal error");
            doGet(request,response);
        }
    }
}
