package com.dwbook.phonebook;

import com.dwbook.phonebook.dao.UserDAO;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;

public class PhonebookAuthenticator implements Authenticator<BasicCredentials, Boolean> {
    private final UserDAO userDAO;
    public PhonebookAuthenticator(DBI jdbi){
        userDAO = jdbi.onDemand(UserDAO.class);
    }

    @Override
    public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
        boolean validUser = (userDAO.getUser(c.getUsername(), c.getPassword()) == 1);
        if(validUser){
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
