package com.vladsimonenko.moviescore.controller;


import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.command.CommandType;
import com.vladsimonenko.moviescore.util.ConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);

        var connection = ConnectionManager.get();
        try {
            request.setAttribute("a", connection.getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);

    }
}
