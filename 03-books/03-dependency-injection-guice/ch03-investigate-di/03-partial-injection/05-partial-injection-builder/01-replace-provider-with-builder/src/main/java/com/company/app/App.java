package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

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
    static class AppleBuilder{
        public Apple build(){
            return new Apple();
        }
    }
    static class Granny{
        private AppleBuilder builder;
        @Inject
        public Granny(AppleBuilder builder){
            this.builder = builder;
        }
        public void eat(){
            builder.build().consume();
            builder.build().consume();
        }
    }
    //# Provider example
//    static class Granny{
//        private Provider<Apple> appleProvider;
//        @Inject
//        public Granny(Provider<Apple> provider){
//            this.appleProvider = provider;
//        }
//        public void eat(){
//            appleProvider.get().consume();
//            appleProvider.get().consume();
//        }
//    }

    /*
    output:
    #Apple.consume(); count=1; id=1
    #Apple.consume(); count=2; id=2
     */
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Granny.class).eat();
    }
}
