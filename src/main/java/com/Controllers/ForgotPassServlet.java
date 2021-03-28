package com.Controllers;

import com.Services.CandidatService;
import com.Services.Mail;
import com.Utils.Md5;
import com.models.Candidat;
import com.models.Compte;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgotPassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Forget Password");
        request.setAttribute("component", "login");
        getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Compte account = CandidatService.getCandidatByEmail((String)request.getParameter("email"));

            if(account == null){
                String msg = "Aucun utilisateur avec ce email";
                request.setAttribute("errorMessage",msg+(String)request.getParameter("email"));
            }
            else{
                Candidat candidat = CandidatService.getCandidatByIdCompte(account.getId());
                String msgBody = Mail.msgForgotPass(candidat.getNomComplet(),"", account.getMoteDePasse());
                Mail.send((String)request.getParameter("email"),"[JobBoard] : Forgot Password",msgBody);
                String msg = "Please verify your email to get your password";
                request.setAttribute("successMessage",msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("title", "Forget Password");
        request.setAttribute("component", "login");
        getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);

    }
}
