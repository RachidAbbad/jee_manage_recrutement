package com.Controllers;

import com.Services.*;
import com.Utils.AppHibernate;
import com.models.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileCandidatServlet", value = "/ProfileCandidatServlet")
public class ProfileCandidatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
            return;
        }

        Integer candidatId = Integer.parseInt(request.getParameter("id"));

        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            // get candidat
            Candidat candidat = CandidatService.getCandidatById(candidatId);

            if (candidat == null) {
                // redirect to 404
                return;
            }

            Compte compte = CompteService.getCompteById(candidat.getIdCompte());
            Cv cv = CvService.getCvByCandidatId(candidatId);

            request.setAttribute("title", "Candidat");
            request.setAttribute("component", "profile-candidat");
            request.setAttribute("candidat", candidat);
            request.setAttribute("cv", cv);
            request.setAttribute("compte", compte);

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
