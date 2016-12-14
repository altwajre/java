package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;

interface Host{
    void hostLog();
}
interface Symbiote{
    void symbioteLog();
}
class HostImpl implements Host{
    private final Symbiote symbiote;
    @Inject
    public HostImpl(Symbiote symbiote){
        this.symbiote = symbiote;
    }
    public void hostLog() {
        System.out.println("#HostImpl.hostLog()");
    }
}
class SymbioteImpl implements Symbiote{
    private final Host host;
    @Inject
    public SymbioteImpl(Host host){
        this.host = host;
    }
    public void symbioteLog() {
        System.out.println("#SymbioteImpl.symbioteLog()");
    }
}
class MyModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Host.class).to(HostImpl.class).in(Singleton.class);
        bind(Symbiote.class).to(SymbioteImpl.class);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(HostImpl.class).hostLog();
    }
}
/*
output:
#HostImpl.hostLog()
 */
