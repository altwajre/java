package com.company.app;

abstract class Command{
    protected Receiver receiver;
    public Command(Receiver receiver){
        this.receiver = receiver;
    }
    public abstract void execute();
}
class ConcreteCommand extends Command{
    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }
    @Override
    public void execute() {
        receiver.Action();
    }
}
class Receiver{
    public void Action(){
        System.out.println("Called Receiver.Action()");
    }
}
class Invoker{
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void executeCommand(){
        command.execute();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.executeCommand();
    }
}
/*
Definition
Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log
requests, and support undoable operations.

output:
Called Receiver.Action()
 */
