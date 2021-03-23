package com.Controllers;

import com.Services.PostulationService;
import com.Services.RecruteurService;
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

@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    Compte compte;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                userEmail = cookie.getValue();
                break;
            }
        }
        SessionFactory factory = AppHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Compte.class)
                    .add(Restrictions.eq("email", userEmail));
            compte = (Compte) criteria.uniqueResult();

            // CANDIDAT
            if (compte.getTypeCompte().equals("Candidat")) {
                Criteria criteria2 = session.createCriteria(Candidat.class)
                        .add(Restrictions.eq("idCompte", compte.getId()));
                Candidat candidat = (Candidat) criteria2.uniqueResult();

                Criteria criteria3 = session.createCriteria(Cv.class)
                        .add(Restrictions.eq("idCandidat", candidat.getId()));
                Cv cv = (Cv) criteria3.uniqueResult();

                request.setAttribute("component", "dashboardCandidat");
                request.setAttribute("candidat", candidat);
                request.setAttribute("cv", cv);

            // RECRUTEUR
            } else {
                Criteria criteria2 = session.createCriteria(Recruteur.class)
                        .add(Restrictions.eq("idCompte", compte.getId()));
                Recruteur recruteur = (Recruteur) criteria2.uniqueResult();

                // get offres of recruteur
                List<Offre> myOffres = RecruteurService.getOffresOfRecruteur(recruteur.getId());

                request.setAttribute("component", "dashboardRecruteur");
                request.setAttribute("recruteur", recruteur);
                request.setAttribute("listOffres", myOffres);
            }

            session.getTransaction().commit();

            request.setAttribute("title", "Dashboard");
            request.setAttribute("compte", compte);
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
