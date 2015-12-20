package com.company.test.common;

import com.company.test.moduleobject.*;

import java.util.HashMap;
import java.util.Map;

public final class Consts {
    public static final String SAUCELAB_USERNAME = "gd_playground";
    public static final String SAUCELAB_KEY = "";
    public static final String URL_LOCAL = "http://localhost:4444/wd/hub";
    public static final String URL_SAUCELAB = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", SAUCELAB_USERNAME, SAUCELAB_KEY);

    public static final Map<String, Class> MODULE_TESTS;
    static {
        MODULE_TESTS = new HashMap<String, Class>();
        MODULE_TESTS.put("desktop-vsc-domain-search-banner-module", DesktopDomainSearchBannerModule.class);
        MODULE_TESTS.put("mobile-vsc-domain-search-banner-module", MobileDomainSearchBannerModule.class);
        MODULE_TESTS.put("tablet-vsc-domain-search-banner-module", TabletDomainSearchBannerModule.class);

        MODULE_TESTS.put("desktop-vsc-homepage-marquee-module", DesktopHomepageMarqueeModule.class);
        MODULE_TESTS.put("mobile-vsc-homepage-marquee-module", MobileHomepageMarqueeModule.class);
        MODULE_TESTS.put("tablet-vsc-homepage-marquee-module", TabletHomepageMarqueeModule.class);

    }
}