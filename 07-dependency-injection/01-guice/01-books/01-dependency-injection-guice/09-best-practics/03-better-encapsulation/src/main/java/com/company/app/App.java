package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;

class Message{
    public Message(String s, String s1){ }
}
interface Messager{
    void send(Message msg);
}
class EmailMessage {}
class Emailer implements Messager{
    @Override
    public void send(Message msg) {
        EmailMessage email = convert(msg);
        // send message via email...
    }
    private EmailMessage convert(Message msg) {
        return new EmailMessage();
    }
}
class JabberTransport {}
class JabberMessageConverter {}
class JabberMessage {}
class JabberMessager implements Messager{
    final JabberTransport transport;
    final JabberMessageConverter converter;
    @Inject
    public JabberMessager(JabberTransport transport, JabberMessageConverter converter) {
        this.transport = transport;
        this.converter = converter;
    }
    @Override
    public void send(Message msg) {
        JabberMessage jabberMessage = convert(msg);
    }
    private JabberMessage convert(Message msg) {
        return new JabberMessage();
    }
}
class MessagingModule extends AbstractModule{
    @Override
    protected void configure() {
//        bind(Messager.class).to(JabberMessager.class);
        bind(Messager.class).annotatedWith(Im.class).to(JabberMessager.class);
        bind(Messager.class).annotatedWith(Mail.class).to(Emailer.class);
    }
}
@interface Im {}
@interface Mail {}
class MessageClient{
    final Messager mailMessager;
    final Messager imMessager;
    public MessageClient(Messager mailMessager, Messager imMessager) {
        this.mailMessager = mailMessager;
        this.imMessager = imMessager;
    }
    public void go(){
        Message msg = new Message("Dhanji", "Hi there!");
        imMessager.send(msg);
        mailMessager.send(msg);
    }
}
public class App {
    public static void main(String... args){
        System.out.println("hi");
    }
}
