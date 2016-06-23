package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

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
class AppleBuilder{
    public Apple build(){
        return new Apple();
    }
}
class Granny{
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
//    class Granny{
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
public class App
{

    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Granny.class).eat();
    }
}
/*
output:
#Apple.consume(); count=1; id=1
#Apple.consume(); count=2; id=2
 */
