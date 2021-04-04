package com.Controllers;

import com.Services.*;
import com.Utils.AppContext;
import com.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                request.setAttribute("successMessage", "Your Postulation has added successfully");
                rederection(request,response);
            } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("errorMessage", exception.getMessage());
            request.setAttribute("etat", "2");
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Candidat c = CandidatService.getCandidatById(candidatId);
                    Offre o = OffreService.getOffreById(offreId);
                    Recruteur r = RecruteurService.getRecruteurById(o.getIdRecruteur());

                    String msgCand = Mail.msgApplyJob(c.getNomComplet(),"",o.getTitre());
                    String msgRec = Mail.MsgApplyJobMsgToRecu(c.getNomComplet(),"",o.getTitre(),r.getNom(),"");

                    Mail.send(CompteService.getCompteById(r.getIdCompte()).getEmail(),"[JobBoard] "+c.getNomComplet()+" has just applied to your job offer",msgRec);
                    Mail.send(CompteService.getCompteById(c.getIdCompte()).getEmail(),"[JobBoard] You just applied to job offer : "+o.getTitre(),msgCand);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        emailExecutor.shutdown();


    }
    private void rederection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") == null) {
            response.sendRedirect("/");
            return;
        }

        Integer offreId = Integer.parseInt(request.getParameter("id"));

        try {
            // get offre
            Offre offre = OffreService.getOffreById(offreId);

            if (offre == null) {
                // redirect to 404
                response.sendRedirect("/");
                return;
            }

            List<Postulation> mesPostulations = PostulationService.getPostulationsByOffreId(offre.getId());

            request.setAttribute("title", "Offer Details");
            request.setAttribute("component", "job-detail");
            request.setAttribute("offre", offre);
            request.setAttribute("listPostulations", mesPostulations);

            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            request.setAttribute("title", "Offer Details");
            request.setAttribute("component", "job-detail");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }


    }
}
