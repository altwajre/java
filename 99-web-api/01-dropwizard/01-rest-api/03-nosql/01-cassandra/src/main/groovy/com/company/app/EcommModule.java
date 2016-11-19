package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class EcommModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    public DataSourceConfig provideConfig(EcommConfiguration ecommConfiguration) {
        return ecommConfiguration.getDataSourceConfig();
    }
}
