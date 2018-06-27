package com.company.app.not.needed;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;

public enum Gatekeeper {
  INSTANCE;

  Gatekeeper() {
    final IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory(
        "classpath:shiro.ini");
    SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
  }

  public SecurityManager securityManager() {
    return SecurityUtils.getSecurityManager();
  }
}
