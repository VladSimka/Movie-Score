package com.vladsimonenko.moviescore.command;

import com.vladsimonenko.moviescore.command.impl.DefaultCommand;
import com.vladsimonenko.moviescore.command.impl.LoginCommand;
import com.vladsimonenko.moviescore.command.impl.LogoutCommand;
import com.vladsimonenko.moviescore.command.impl.RegisterCommand;

public enum CommandType {

    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    DEFAULT(new DefaultCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String command) {
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return commandType.command;
    }
}
