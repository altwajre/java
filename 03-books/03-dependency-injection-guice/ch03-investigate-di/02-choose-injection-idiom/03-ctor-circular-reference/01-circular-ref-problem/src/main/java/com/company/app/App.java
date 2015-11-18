package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

class Host{
    private final Symbiote symbiote;
    @Inject
    public Host(Symbiote symbiote){
        this.symbiote = symbiote;
    }
    public void log(){
        System.out.println("Host.log()");
    }
}
class Symbiote{
    private final Host host;
    @Inject
    public Symbiote(Host host){
        this.host = host;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Host.class).log();
    }
}
/*
3.2.3. The circular reference problem

output:
Exception in thread "main" com.google.inject.ProvisionException: Unable to provision, see the following errors:

1) Tried proxying com.company.app.App$Host to support a circular dependency, but it is not an interface.
  while locating com.company.app.App$Host
    for parameter 0 at com.company.app.App$Symbiote.<init>(App.java:21)
  while locating com.company.app.App$Symbiote
    for parameter 0 at com.company.app.App$Host.<init>(App.java:11)
  while locating com.company.app.App$Host
 */
