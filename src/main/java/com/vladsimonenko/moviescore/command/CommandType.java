package com.vladsimonenko.moviescore.command;

import com.vladsimonenko.moviescore.command.impl.DefaultCommand;
import com.vladsimonenko.moviescore.command.impl.LoginCommand;
import com.vladsimonenko.moviescore.command.impl.LogoutCommand;
import com.vladsimonenko.moviescore.command.impl.RegisterCommand;
import com.vladsimonenko.moviescore.command.impl.film.GoToFilmPageCommand;
import com.vladsimonenko.moviescore.command.impl.user.AddReviewCommand;
import com.vladsimonenko.moviescore.command.impl.user.GoToUserPageCommand;

public enum CommandType {

    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    DEFAULT(new DefaultCommand()),
    GO_TO_USER_PAGE(new GoToUserPageCommand()),
    GO_TO_FILM_PAGE(new GoToFilmPageCommand()),
    ADD_REVIEW(new AddReviewCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String command) {
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return commandType.command;
    }
}
