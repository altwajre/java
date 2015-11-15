package com.company.app;

import com.google.inject.*;

public class App
{
    static int count = 0;
    static int appleId = 0;
    static class Apple{
        private int id;
        public Apple(){
            id = ++appleId;
        }
        public void consume(){
            count++;
            System.out.println("#Apple.consume(); count=" + count + "; id=" + id);
        }
    }
    static class Granny{
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
    static class MyModule extends AbstractModule{
        @Override
        protected void configure() {
            // when bind to Singleton, the same instance is returned everytime.
            bind(Apple.class).in(Singleton.class);
        }
    }
    /*
    output: ids are same
    #Apple.consume(); count=1; id=1
    #Apple.consume(); count=2; id=1
     */
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(Granny.class).eat();
    }
}
