package com.company.app;

abstract class State{
    public abstract void handle(Context context);
}
class ConcreteStateA extends State{
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}
class ConcreteStateB extends State{
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}
class Context{
    private State state;
    public Context(State state){
        this.state = state;
    }
    public State getState(){
        return state;
    }
    public void setState(State value){
        this.state = value;
        System.out.println("State: " + state.getClass().getSimpleName());
    }
    public void request(){
        state.handle(this);
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Setup context in a state
        Context c = new Context(new ConcreteStateA());
        // Issue requests, which toggle state
        c.request();
        c.request();
        c.request();
        c.request();
    }
}
/*
Definition
Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

output:
State: ConcreteStateB
State: ConcreteStateA
State: ConcreteStateB
State: ConcreteStateA
 */
