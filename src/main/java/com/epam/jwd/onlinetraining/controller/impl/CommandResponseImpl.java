package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

import java.util.Objects;

public class CommandResponseImpl implements CommandResponse {
    private final boolean redirect;
    private final String path;

    public CommandResponseImpl(String path) {
        this(false, path);
    }

    public CommandResponseImpl(boolean redirect, String path) {
        this.redirect = redirect;
        this.path = path;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResponseImpl that = (CommandResponseImpl) o;
        return redirect == that.redirect && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirect, path);
    }

    @Override
    public String toString() {
        return "PlainCommandResponse{" +
                "redirect=" + redirect +
                ", path='" + path + '\'' +
                '}';
    }
}
