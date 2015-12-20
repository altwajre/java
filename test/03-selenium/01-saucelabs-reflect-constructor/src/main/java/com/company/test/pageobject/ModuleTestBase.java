package com.company.test.pageobject;

import com.company.test.common.Consts;
import com.company.test.common.PlatformType;
import com.company.test.moduleobject.SitecoreModule;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class ModuleTestBase {
    protected WebDriver driver;
    protected PlatformType platformType;
    protected void performModuleTest(String moduleClassName) {
        String moduleTestKey = new StringBuilder()
                .append(platformType.toString())
                .append("-")
                .append(moduleClassName)
                .toString();
        System.out.println(moduleTestKey);

        try {
            /*
             If the appropriate constructor has no parameters, then you don’t even need to use java.lang.reflect;
             the Class.newInstance method provides the required functionality.
             */
            Class<?> clazz = Consts.MODULE_TESTS.get(moduleTestKey);
            if(clazz == null){
                return;
            }
            Constructor<?> ctor = clazz.getConstructor(WebDriver.class);
            SitecoreModule sitecoreModule = (SitecoreModule)ctor.newInstance(new Object[]{driver});
            sitecoreModule.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
