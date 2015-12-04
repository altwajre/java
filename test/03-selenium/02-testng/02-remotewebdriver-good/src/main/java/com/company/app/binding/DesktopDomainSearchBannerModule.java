package com.company.app.binding;

import com.company.app.testbase.DesktopDomainSearchBannerModuleTest;
import com.company.app.testbase.ModuleTest;

import com.google.inject.AbstractModule;

public class DesktopDomainSearchBannerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ModuleTest.class).to(DesktopDomainSearchBannerModuleTest.class);
    }
}
