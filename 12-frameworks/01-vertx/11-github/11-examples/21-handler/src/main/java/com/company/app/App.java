package com.company.app;

import io.vertx.core.Handler;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

/*
@FunctionalInterface
public interface Handler<E> {
  void handle(E var1);
}

same as
public interface Consumer<T>{
  void accept(T t);
}
 */
class HandlerCompany {

    public void run(){
        HandlerBuilder builder = new HandlerBuilder("Office building");
        builder.handler(this::build);
    }

    public void build(String name){
        System.out.println("HandlerCompany: build " + name);
    }
}

@Data
@AllArgsConstructor
class HandlerBuilder {
    private String name;

    public void handler(Handler<String> handler) {
        handler.handle(name);
    }
}

class ConsumerCompany {

    public void run(){
        ConsumerBuilder builder = new ConsumerBuilder("House");
        builder.handler(this::build);
    }

    public void build(String name){
        System.out.println("ConsumerCompany: build " + name);
    }
}

@Data
@AllArgsConstructor
class ConsumerBuilder {
    private String name;

    public void handler(Consumer<String> callback) {
        callback.accept(name);
    }

}

public class App
{

    public static void main( String[] args )
    {
        HandlerCompany handlerCompany = new HandlerCompany();
        handlerCompany.run();

        ConsumerCompany consumerCompany = new ConsumerCompany();
        consumerCompany.run();

    }
}
/*
output:
HandlerCompany: build Office building
ConsumerCompany: build House
 */
