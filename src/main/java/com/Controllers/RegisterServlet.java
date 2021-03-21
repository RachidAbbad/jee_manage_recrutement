package com.Controllers;

import com.Services.RegisterCandidatService;
import com.Services.RegisterRecruService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Home");
        request.setAttribute("component", "register");
        getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // general information
        String telephone = request.getParameter("register_numtel");
        String ville = request.getParameter("register_ville");
        String email = request.getParameter("register_email");
        String mot_de_passe = request.getParameter("register_password");
        String type_compte = request.getParameter("type_compte");

        try {
            if (type_compte.equals("Candidat")) {
                String nomcomplet = request.getParameter("register_nomcomplet");
                String titreemploi = request.getParameter("register_titreemploi");
                String civilite = request.getParameter("register_civilite");

                RegisterCandidatService.register(ville, email, mot_de_passe, telephone, 0, type_compte, nomcomplet, titreemploi, "", civilite);
            } else {
                String nom = request.getParameter("register_nom_rec");
                String registerDescRec = request.getParameter("register_desc_rec");
                String registerSiteweb = request.getParameter("register_siteweb");

                RegisterRecruService.register(ville, email, mot_de_passe, telephone, 0, type_compte, nom, registerDescRec, registerSiteweb);
            }

            // redirect to register with success message
            request.setAttribute("title", "Home");
            request.setAttribute("component", "register");
            request.setAttribute("successMessage", "Register has been done successfully. Please verify your email.");
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();

            // redirect to register with error message
            request.setAttribute("title", "Home");
            request.setAttribute("component", "register");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }
    }
}
