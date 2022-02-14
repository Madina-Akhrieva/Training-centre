package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.List;


/**
 * com.epam.jwd.onlinetraining.controller.command.coursecomamnd public enum ShowAccountsPageCommand
 * extends Enum<ShowAccountsPageCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowAccountsPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final String JSP_ACCOUNTS_ATTRIBUTE_NAME = "accounts";
    private static final String ACCOUNTS_PAGE = "page.accounts";
    private final AccountService accountService;
    private static final String INDEX_JSP_PATH = "page.index";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAccountsPageCommand(AccountService accountService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            List<Account> accounts = accountService.findAll();
            request.addAttributeToJsp(JSP_ACCOUNTS_ATTRIBUTE_NAME, accounts);
            return requestFactory.createForwardResponse(propertyContext.get(ACCOUNTS_PAGE));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }

    }
}
