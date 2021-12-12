package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.dao.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.onlinetraining.dao.model.Role.ADMIN;
import static com.epam.jwd.onlinetraining.dao.model.Role.MENTOR;
import static com.epam.jwd.onlinetraining.dao.model.Role.STUDENT;
import static com.epam.jwd.onlinetraining.dao.model.Role.UNAUTHORIZED;

public enum CommandRegistry {

    MAIN_PAGE(ShowMainPageCommand.INSTANCE, "main_page"),
    ERROR(ShowErrorPageCommand.INSTANCE, "show_error"),
    LOGIN_PAGE(ShowLoginPageCommand.INSTANCE, "show_login", UNAUTHORIZED),
    SIGN_PAGE(ShowSignupPageCommand.INSTANCE, "show_signup"),
    LOGIN(LoginCommand.INSTANCE, "login", UNAUTHORIZED),
    LOGOUT(LogoutCommand.INSTANCE, "logout", STUDENT, MENTOR, ADMIN),
    PROFILE_PAGE(ShowProfilePageCommand.INSTANCE, "show_profile"),
    SHOW_ACCOUNTS(ShowAccountsPageCommand.INSTANCE, "show_accounts", ADMIN),
    DEFAULT(ShowMainPageCommand.INSTANCE, "");

    private final Command command;
    private final String path;
    private final List<Role> allowedRoles;

    CommandRegistry(Command command, String path, Role... roles) {
        this.command = command;
        this.path = path;
        this.allowedRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
