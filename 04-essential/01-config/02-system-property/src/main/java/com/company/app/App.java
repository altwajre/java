package com.company.app;

public class App {
  public static void main(String[] args) {

    System.out.println("# set/get property");
    System.setProperty("com.company.version", "0.0.1");
    System.out.println(System.getProperty("com.company.version"));
    System.out.println("");

    System.out.println("# get path.separator");
    System.out.println(System.getProperty("path.separator"));
    System.out.println("");

    System.out.println("# get System.out");
    System.getProperties().list(System.out);

  }
}
/*
# set/get property
0.0.1

# get path.separator
:

# get System.out
-- listing properties --
java.runtime.name=Java(TM) SE Runtime Environment
sun.boot.library.path=/Library/Java/JavaVirtualMachines/jdk...
java.vm.version=25.45-b02
gopherProxySet=false
java.vm.vendor=Oracle Corporation
java.vendor.url=http://java.oracle.com/
 */
