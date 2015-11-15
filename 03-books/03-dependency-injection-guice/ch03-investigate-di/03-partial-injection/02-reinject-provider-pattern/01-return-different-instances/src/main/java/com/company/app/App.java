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
    /*
    output: ids are different
    #Apple.consume(); count=1; id=1
    #Apple.consume(); count=2; id=2
     */
    public static void main( String[] args )
    {
        // default Guice support return different instance each time
        Guice.createInjector().getInstance(Granny.class).eat();
    }
}
