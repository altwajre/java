package com.company.app;

import com.google.inject.*;

class Globals{
    public static int count = 0;
    public static int appleId = 0;
}
class Apple{
    private int id;
    public Apple(){
        id = ++Globals.appleId;
    }
    public void consume(){
        Globals.count++;
        System.out.println("#Apple.consume(); count=" + Globals.count + "; id=" + id);
    }
}
class Granny{
    private Provider<Apple> appleProvider;
    @Inject
    public Granny(Provider<Apple> provider){
        this.appleProvider = provider;
    }
    public void eat(){
        appleProvider.get().consume();
        appleProvider.get().consume();
    }
}
class MyModule extends AbstractModule{
    @Override
    protected void configure() {
        // when bind to Singleton, the same instance is returned everytime.
        bind(Apple.class).in(Singleton.class);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(Granny.class).eat();
    }
}
/*
output: ids are same
#Apple.consume(); count=1; id=1
#Apple.consume(); count=2; id=1
 */
