package com.Controllers;

import com.Services.CandidatService;
import com.Services.CompteService;
import com.Services.RecruteurService;
import com.Utils.AppContext;
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


@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
@MultipartConfig
public class DashboardServlet extends HttpServlet {
    Compte compte;
    private File file;

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
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            session.getTransaction().rollback();
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int compteId = AppContext.isAthorized(request);

        if (compteId == -1) {
            return;
        }

        String typeCompte = request.getParameter("type_compte");
        String ville = request.getParameter("ville");
        String numTel = request.getParameter("num_tel");
        String password = request.getParameter("password");

        if (typeCompte.equals("Candidat")) {
            int candidatId = CandidatService.isCandidat(compteId);

            if (candidatId == -1) {
                return;
            }

            String civilite = request.getParameter("civilite");
            String nomComplet = request.getParameter("nom_complet");
            String titreEmploi = request.getParameter("titre_emploi");

            try {
                // upload photo
                String photoUrl = CompteService.uploadPhoto(compteId, request);

                // update
                CandidatService.updateCandidat(compteId, candidatId, password, ville, numTel, civilite, nomComplet, titreEmploi, photoUrl);

                response.sendRedirect("/dashboard");
            } catch (Exception exception) {
                exception.printStackTrace();

                return;
                // request.setAttribute("errorMessage", exception.getMessage());
                // request.setAttribute("component", "dashboard");
                // getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            }
        } else {
            int recruteurId = RecruteurService.isRecruteur(compteId);

            if (recruteurId == -1) {
                return;
            }

            String siteweb = request.getParameter("siteweb");
            String nomRecr = request.getParameter("nom");
            String descRecr = request.getParameter("desc");

            try {
                // upload logo
                String logoUrl = CompteService.uploadPhoto(compteId, request);

                RecruteurService.updateRecruteur(compteId, recruteurId, password, ville, numTel, siteweb, nomRecr, descRecr, logoUrl);

                response.sendRedirect("/dashboard");
            } catch (Exception exception) {
                exception.printStackTrace();

                request.setAttribute("errorMessage", exception.getMessage());
                getServletContext().getRequestDispatcher("/dashboard").forward(request, response);
            }
        }
    }
    public void hasExternalMsg(String msg,boolean msgType,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(msgType)
            request.setAttribute("successMessage",msg);
        else
            request.setAttribute("errorMessage",msg);
        this.doGet(request,response);
    }
}
