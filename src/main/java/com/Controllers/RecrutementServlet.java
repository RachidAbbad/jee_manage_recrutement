package com.Controllers;

import com.Services.CandidatService;
import com.Services.CompteService;
import com.Services.RecrutementService;
import com.Services.RecruteurService;
import com.Utils.AppHibernate;
import com.models.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
            if(RecrutementService.addRecrutement(recruteurId, candidatId, offreId))
                redirectToDashboard("You have been recruit "+ CandidatService.getCandidatById(candidatId).getNomComplet(),true,request,response);
            else
                redirectToDashboard("Error has occurred",false,request,response);


        } catch (Exception exception) {
            exception.printStackTrace();
            redirectToDashboard(exception.getMessage(),false,request,response);

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void redirectToDashboard(String recrutement_added_successfully, boolean b, HttpServletRequest request, HttpServletResponse response) {
        Compte compte;
        File file;

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

                // cv
                Criteria criteria3 = session.createCriteria(Cv.class)
                        .add(Restrictions.eq("idCandidat", candidat.getId()));
                Cv cv = (Cv) criteria3.uniqueResult();

                if (cv != null) {
                    // formations
                    Query query = session.createQuery("from Formation where id_cv = :cvid").setParameter("cvid", cv.getId());
                    List<Formation> formations = query.list();
                    request.setAttribute("formations", formations);

                    // experiences
                    Query query2 = session.createQuery("from Experience where id_cv = :cvid").setParameter("cvid", cv.getId());
                    List<Experience> experiences = query2.list();
                    request.setAttribute("experiences", experiences);

                    // projets
                    Query query3 = session.createQuery("from Projet where id_cv = :cvid").setParameter("cvid", cv.getId());
                    List<Projet> projets = query3.list();
                    request.setAttribute("projets", projets);

                    // competences
                    Query query4 = session.createQuery("from Competence where id_cv = :cvid").setParameter("cvid", cv.getId());
                    List<Competence> competences = query4.list();
                    request.setAttribute("competences", competences);
                }

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
            if(b)
                request.setAttribute("successMessage",recrutement_added_successfully);
            else
                request.setAttribute("errorMessage",recrutement_added_successfully);

            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
        }


    }
}
