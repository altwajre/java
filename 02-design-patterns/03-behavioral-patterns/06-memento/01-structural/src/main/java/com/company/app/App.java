package com.company.app;

class Memnto{
    private String state;
    public Memnto(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
}
class Caretaker{
    private Memnto memnto;
    public Memnto getMemnto(){
        return this.memnto;
    }
    public void setMemnto(Memnto memnto){
        this.memnto = memnto;
    }
}
class Originator{
    private String state;
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
        System.out.println("State = " + state);
    }
    public Memnto createMemento(){
        return new Memnto(state);
    }
    public void setMemento(Memnto memento){
        System.out.println("Restoring state...");
        state = memento.getState();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Originator o = new Originator();
        o.setState("On");

        // Store internal state
        Caretaker c = new Caretaker();
        c.setMemnto(o.createMemento());

        // Continue changing originator
        o.setState("Off");

        // Restore saved state
        o.setMemento(c.getMemnto());

        System.out.println("State = " + o.getState());
    }
}
/*
Definition
Without violating encapsulation, capture and externalize an object's internal state so that the object can be restored
to this state later.

output:
State = On
State = Off
Restoring state...
State = On
 */
