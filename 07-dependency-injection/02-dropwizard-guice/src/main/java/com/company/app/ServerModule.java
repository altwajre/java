package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Named("message")
    public String provideMessage(ServerConfiguration serverConfiguration){
        return serverConfiguration.getMessage();
    }
}
