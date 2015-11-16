package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

/*
AppleBuilder now encapsulates the construction and any implementation details of Apple (red, green, or otherwise).
The dependent, Granny, is kept completely free of implementation details.
 */
public class App
{
    static int redAppleCount = 0;
    static int redAppleId = 0;
    interface Apple{
        void consume();
    }
    static class RedApple implements Apple {
        private int id;
        public RedApple(){
            id = ++redAppleId;
        }
        public void consume(){
            redAppleCount++;
            System.out.println("#RedApple.consume(); redAppleCount=" + redAppleCount + "; id=" + id);
        }
    }
    static class GreenApple implements Apple{
        public void consume(){
            System.out.println("#GreenApple.consume()");
        }
    }
    static class AppleBuilder{
        public Apple buildRedApple(){
            return new RedApple();
        }
        public Apple buildGreenApple(){
            return new GreenApple();
        }
    }
    static class Granny{
        private AppleBuilder builder;
        @Inject
        public Granny(AppleBuilder builder){
            this.builder = builder;
        }
        public void eat(){
            builder.buildRedApple().consume();
            builder.buildRedApple().consume();
            builder.buildGreenApple().consume();
        }
    }

    /*
    output:
    #RedApple.consume(); redAppleCount=1; id=1
    #RedApple.consume(); redAppleCount=2; id=2
    #GreenApple.consume()
     */
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Granny.class).eat();
    }
}
