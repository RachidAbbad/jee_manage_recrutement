package com.Controllers;

import com.Services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Login");
        request.setAttribute("component", "login");
        getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get login information
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (LoginService.Login(email, password)) {
                // redirect to profile
                Cookie cookie = new Cookie("user", email);
                response.addCookie(cookie);
                response.sendRedirect("/dashboard");

            } else {
                // information wrong
                request.setAttribute("title", "Home");
                request.setAttribute("component", "login");
                request.setAttribute("errorMessage", "Information wrong");
                getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();

            request.setAttribute("title", "Home");
            request.setAttribute("component", "login");
            request.setAttribute("errorMessage", exception.getMessage());
            getServletContext().getRequestDispatcher("/App.jsp").forward(request, response);
        }
    }
}
