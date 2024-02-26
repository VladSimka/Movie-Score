package com.vladsimonenko.moviescore.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/jsp/*")
public class GoToMainPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/controller?command=default");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
}
