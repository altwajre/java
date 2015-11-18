package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

class Globals{
    public static int redAppleCount = 0;
    public static int redAppleId = 0;
}
interface Apple{
    void consume();
}
class RedApple implements Apple {
    private int id;
    public RedApple(){
        id = ++Globals.redAppleId;
    }
    public void consume(){
        Globals.redAppleCount++;
        System.out.println("#RedApple.consume(); redAppleCount=" + Globals.redAppleCount + "; id=" + id);
    }
}
class GreenApple implements Apple{
    public void consume(){
        System.out.println("#GreenApple.consume()");
    }
}
class AppleBuilder{
    public Apple buildRedApple(){
        return new RedApple();
    }
    public Apple buildGreenApple(){
        return new GreenApple();
    }
}
class Granny{
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
public class App
{

    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Granny.class).eat();
    }
}
/*
AppleBuilder now encapsulates the construction and any implementation details of Apple (red, green, or otherwise).
The dependent, Granny, is kept completely free of implementation details.

output:
#RedApple.consume(); redAppleCount=1; id=1
#RedApple.consume(); redAppleCount=2; id=2
#GreenApple.consume()
 */
