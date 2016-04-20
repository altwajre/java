package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

class Box{
    final int width;
    final int height;
    final int depth;
    // annotating the constructor with @Inject isn't enough. Guice would see three int(s) it doesn't know how to inject.
    // We must distinguish them somehow. @Named annotation is one way to do this:
    @Inject
    public Box(@Named("width") final int width, @Named("height") int height, @Named("depth") int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public int surfaceArea(){
        return (width * height + width * depth + height * depth) * 2;
    }
    public int volume(){
        return width * height * depth;
    }
}
class BoxModule extends AbstractModule {
    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("width")).to(2);
        bindConstant().annotatedWith(Names.named("height")).to(3);
        bindConstant().annotatedWith(Names.named("depth")).to(4);
        bind(Box.class);
    }
}
public class App {
    public static void main(String... args){
        Injector injector = Guice.createInjector(new BoxModule());
        Box box = injector.getInstance(Box.class);
        System.out.println(box.volume());
    }
}
/*
http://blog.muhuk.com/2015/05/28/using_guice_effectively.html#.VxcZ4eYrJTY

output:
24
 */
