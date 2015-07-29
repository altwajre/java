package com.company.app.infrastructure;

public final class Consts {
    public static final String SAUCELAB_USERNAME = Global.Config.getUsername();
    public static final String SAUCELAB_KEY = Global.Config.getKey();
    public static final String URL_LOCAL = "http://localhost:4444/wd/hub";
    public static final String URL_SAUCELAB = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", SAUCELAB_USERNAME, SAUCELAB_KEY);
}
