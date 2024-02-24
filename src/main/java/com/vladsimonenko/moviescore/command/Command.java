package com.vladsimonenko.moviescore.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}
