package com.company.app;
import java.util.HashMap;
import java.util.Map;

class Participant{  // Abstract Colleague
    public String Name;
    public Chatroom Chatroom;
    public void send(String to, String message){ Chatroom.send(Name, to, message); }
    public void receive(String from, String message){ System.out.format("%s to %s: %s\n", from, Name, message); }
}
class Beatle extends Participant{  // Concrete Colleague
    public Beatle(String name){ this.Name = name; }
    @Override
    public void receive(String from, String message){
        System.out.println("To a Beatle: ");
        super.receive(from, message);
    }
}
class NonBeatle extends Participant{
    public NonBeatle(String name){ this.Name = name; }
    public void receive(String from, String message){
        System.out.println("To a non-Beatle: ");
        super.receive(from, message);
    }
}
interface IChatroom{  // Mediator interface
    void register(Participant participant);
    void send(String from, String to, String message);
}
class Chatroom implements IChatroom{  // Concrete Mediator
    private Map<String, Participant> participants = new HashMap<String, Participant>();
    public void register(Participant participant) {
        if(!participants.containsKey(participant.Name)){ participants.put(participant.Name, participant); }
        participant.Chatroom = this;
    }
    public void send(String from, String to, String message) {
        Participant participant = participants.get(to);
        if(participant != null){ participant.receive(from, message); }
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Create chatroom participants
        Participant george = new Beatle("George");
        Participant paul = new Beatle("Paul");
        Participant ringo = new Beatle("Ringo");
        Participant john = new Beatle("John");
        Participant yoko = new NonBeatle("Yoko");
        // Create chatroom and register participants
        Chatroom chatroom = new Chatroom();
        chatroom.register(george);
        chatroom.register(paul);
        chatroom.register(ringo);
        chatroom.register(john);
        chatroom.register(yoko);
        // Chatting participants
        yoko.send("John", "Hi John!");
        paul.send("Ringo", "All you need is love");
        ringo.send("George", "My sweet Lord");
        paul.send("John", "Can't buy me love");
        john.send("Yoko", "My sweet love");
    }
}
/*
Definition
Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects
from referring to each other explicitly, and it lets you vary their interaction independently.

output:
To a Beatle:
Yoko to John: Hi John!
To a Beatle:
Paul to Ringo: All you need is love
To a Beatle:
Ringo to George: My sweet Lord
To a Beatle:
Paul to John: Can't buy me love
To a non-Beatle:
John to Yoko: My sweet love
 */