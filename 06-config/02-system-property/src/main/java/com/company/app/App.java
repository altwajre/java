package com.company.app;

public class App {
  public static void main(String[] args) {

    System.out.println("# user.dir: " + System.getProperty("user.dir") + "\n");
    System.out.println("# os.name: " + System.getProperty("os.name") + "\n");

    System.setProperty("com.company.version", "0.0.1");
    System.out.println("# com.company.version: " + System.getProperty("com.company.version") + "\n");

    System.out.println("# path.separator: " + System.getProperty("path.separator") + "\n");

    System.out.println("# get System.out");
    System.getProperties().list(System.out);

  }
}
/*
# user.dir: /Users/whan/Desktop/java/06-config/02-system-property

# os.name: Mac OS X

# com.company.version: 0.0.1

# path.separator: :


# get System.out
-- listing properties --
java.runtime.name=Java(TM) SE Runtime Environment
sun.boot.library.path=/Library/Java/JavaVirtualMachines/jdk...
java.vm.version=25.45-b02
gopherProxySet=false
java.vm.vendor=Oracle Corporation
java.vendor.url=http://java.oracle.com/
 */
