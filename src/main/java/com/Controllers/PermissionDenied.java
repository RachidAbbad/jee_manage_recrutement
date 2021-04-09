package com.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionDenied  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Permission Denied");
        req.setAttribute("component", "permissionDenied");
        getServletContext().getRequestDispatcher("/App.jsp").forward(req,resp);
    }
}
