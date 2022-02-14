package com.epam.jwd.onlinetraining.controller.filter;

import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRegistry;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * com.epam.jwd.onlinetraining.controller.filter @WebFilter(urlPatterns = "/controller")
 * public class RoleFilter
 * extends Object
 * implements Filter
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
@WebFilter(urlPatterns = "/controller")
public class RoleFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(RoleFilter.class);
    private static final String COMMAND_PARAM_NAME = "command";
    private static final String ERROR_PAGE_URL = "/controller?command=show_error";

    private Map<Role, Set<Command>> commandByRoles;

    public RoleFilter() {
        commandByRoles = new EnumMap<Role, Set<Command>>(Role.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        for (CommandRegistry command : CommandRegistry.values()) {
            for (Role allowedRole : command.getAllowedRoles()) {
                Set<Command> commands = commandByRoles.computeIfAbsent(allowedRole, k -> new HashSet<>());
                commands.add(command.getCommand());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandName = request.getParameter(COMMAND_PARAM_NAME);
        LOGGER.trace("Checking permission for command/ commandName : {}", commandName);
        if (currentUserHasPermissionForCommand(commandName, request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(ERROR_PAGE_URL);
        }
    }

    private boolean currentUserHasPermissionForCommand(String commandName, HttpServletRequest httpServletRequest) {
        Role currentAccountRole = retrieveCurrentUserRole(httpServletRequest);
        final Command command = Command.of(commandName);
        Set<Command> allowedCommands = commandByRoles.get(currentAccountRole);
        return allowedCommands.contains(command);
    }

    private Role retrieveCurrentUserRole(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getSession(false))
                .map(s -> (Account) s.getAttribute("account"))
                .map(Account::getRole)
                .orElse(Role.UNAUTHORIZED);

    }
}
