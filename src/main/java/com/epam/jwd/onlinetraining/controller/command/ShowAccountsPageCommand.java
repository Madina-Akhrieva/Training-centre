package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.List;

public enum ShowAccountsPageCommand implements Command{
    INSTANCE(ServiceFactory.simple().accountService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final String JSP_ACCOUNTS_ATTRIBUTE_NAME = "accounts";
    private static final String ACCOUNTS_PAGE = "page.accounts";
    private final AccountService accountService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAccountsPageCommand(AccountService accountService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        List<Account> accounts = accountService.findAll();
        request.addAttributeToJsp(JSP_ACCOUNTS_ATTRIBUTE_NAME, accounts);
        return requestFactory.createForwardResponse(propertyContext.get(ACCOUNTS_PAGE));
    }
}
