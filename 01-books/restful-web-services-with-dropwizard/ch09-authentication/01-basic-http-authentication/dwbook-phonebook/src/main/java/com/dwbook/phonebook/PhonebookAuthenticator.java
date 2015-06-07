package com.dwbook.phonebook;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class PhonebookAuthenticator implements Authenticator<BasicCredentials, Boolean> {
    @Override
    public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
        if(c.getUsername().equals("tom") && c.getPassword().equals("pass")){
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
