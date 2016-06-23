package com.company.app;

import java.util.function.Consumer;

class Mailer{
    private String message;
    public void from(final String address){}
    public void to(final String address){}
    public void subject(final String line){}
    public void body(final String message){this.message = message;}
    public void send(){System.out.println("sending: " + message);}
}
class MailBuilder{
    private String message;
    public MailBuilder from(final String address){return this;}
    public MailBuilder to(final String address){return this;}
    public MailBuilder subject(final String line){return this;}
    public MailBuilder body(final String message){this.message = message; return this;}
    public void send(){System.out.println("sending: " + message);}
}
/*
1, all the non-terminal methods return the instance.
2. made the constructor private
3, made the terminal method send() static method, and it expects a Consumer as a parameter
we can use it to configure mailers, to specify database-connection parameters, or anywhere we need to build a series of
states on an instance, but in a controlled manner.
 */
class FluentMailer{
    private FluentMailer(){ }
    static private StringBuilder stringBuilder = new StringBuilder();
    public FluentMailer from(final String address){return this;}
    public FluentMailer to(final String address){return this;}
    public FluentMailer subject(final String line){return this;}
    public FluentMailer body(final String message){ stringBuilder.append(message); return this;}
    public static void send(final Consumer<FluentMailer> block){
        final FluentMailer mailer = new FluentMailer();
        block.accept(mailer);
        System.out.println("sending: " + stringBuilder.toString());
    }
}
public class App
{
    private static void intuitiveFluent() {
        FluentMailer.send(mailer ->
                mailer.from("tom@gmail.com")
                        .to("harry@gmail.com")
                        .subject("code review")
                        .body("making the api intuitive and fluent")
        );
    }

    public static void main( String[] args )
    {
        System.out.println("#routineVersion");
        routineVersion();

        System.out.println("#methodChaining");
        methodChaining();

        System.out.println("#intuitiveFluent");
        intuitiveFluent();

        System.out.println("#testConsumer");
        testConsumer();
    }

    private static void testConsumer() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("test consumer");
    }

    private static void methodChaining() {
        new MailBuilder()
                .from("tom@gmail.com")
                .to("harry@gmail.com")
                .subject("code review")
                .body("using method chaining")
                .send();
    }

    private static void routineVersion() {
        Mailer mailer = new Mailer();
        mailer.from("tom@gmail.com");
        mailer.to("harry@gmail.com");
        mailer.subject("code review");
        mailer.body("start with simple routing");
        mailer.send();
    }
}
/*
output:
#routineVersion
sending: start with simple routing
#methodChaining
sending: using method chaining
#intuitiveFluent
sending: making the api intuitive and fluent
#testConsumer
test consumer
 */
