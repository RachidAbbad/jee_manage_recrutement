package com.Filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();

        boolean isUser = false;

        // check if user cookie exists
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                isUser = true;
                break;
            }
        }
        if (isUser) response.sendRedirect("/");
        else filterChain.doFilter(request, response);
    }
}
