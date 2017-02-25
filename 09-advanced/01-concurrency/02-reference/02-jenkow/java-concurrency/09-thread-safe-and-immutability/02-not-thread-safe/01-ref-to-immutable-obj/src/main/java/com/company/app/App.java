package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety-and-immutability.html

NOT thread safe:
Even immutable object is thread safe, but the reference to the immutable object may not be thread safe.
The Calculator class holds a reference to an ImmutableObject instance. Notice how it is possible to change that
reference through both the setValue() and add() methods. Therefore, even if the Calculator class uses an immutable
object internally, it is not itself immutable, and therefore not thread safe.
 */
class ImmutableObject{ // thread safe
    private final int value;
    public ImmutableObject(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public ImmutableObject add(int value){
        return new ImmutableObject(this.value + value);
    }
}
class Calculator {
    private ImmutableObject value = null;
    public ImmutableObject getValue(){ return value; }
    public void setValue(ImmutableObject value){
        this.value = value;
    }
    public void add(int value){
        this.value = this.value.add(value);
    }
}
public class App
{
    public static void main( String[] args )
    {
        final Calculator calculator = new Calculator();
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                calculator.setValue(new ImmutableObject(3));
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                calculator.setValue(new ImmutableObject(2));
            }
        }.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(calculator.getValue() != null){
            System.out.println(Thread.currentThread().getName() + " - counter: "
                    + calculator.getValue().getValue());
        }
    }
}
/*
output:
main - counter: 2

or

main - counter: 3
 */
