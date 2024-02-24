package com.vladsimonenko.moviescore.controller.listener;

import com.vladsimonenko.moviescore.util.ConnectionManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Я ТТТУУУУТ БЫЫЫЫл");
        try {

            Class.forName(ConnectionManager.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
