package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Account;

import java.util.Optional;

public interface AccountService extends EntityService<Account>{

    Optional<Account> authenticate(String email, String password);


}
