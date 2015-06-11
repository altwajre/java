package com.company.ecomm.tests.testbase;

import com.company.ecomm.tests.model.Credentials;
import org.ini4j.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Global {

    Logger logger = LoggerFactory.getLogger(Global.class);

    // Run config
    public static String ENV = "test";

    // URIs
    public static String URI_BASE_CATALOG = null;

    // USERS
    public static Credentials MASTER_CREDS_DEFAULT = null;

    // SYSINFO
    public static String SLASH = System.getProperty("file.separator");
    public static String TEST_RESOURCES_PATH = "src" + SLASH + "test" + SLASH + "resources" + SLASH;
    public static String CONFIG_FILE_BASENAME = "app.conf.";
    public static String CONFIG_FILE_PATH = TEST_RESOURCES_PATH + CONFIG_FILE_BASENAME + Global.ENV;

    public static void Initialize() throws IOException {
        Ini ini = new Ini(new File(Global.CONFIG_FILE_PATH));

        Global.MASTER_CREDS_DEFAULT = new Credentials(ini.get("creds_admin", "accessKey"), ini.get("creds_admin", "secretKey"));
    }
}
