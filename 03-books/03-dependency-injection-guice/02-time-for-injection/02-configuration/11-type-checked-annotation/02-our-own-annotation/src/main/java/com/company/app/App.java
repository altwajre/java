package com.company.app;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;

// Although @Named is convenient, using string literals in a large codebase will cause maintenance issues.
// Instead we can create our own annotations.
@BindingAnnotation
@Target({PARAMETER, METHOD})
@Retention(RUNTIME)
@interface Width{}

@BindingAnnotation
@Target({PARAMETER, METHOD})
@Retention(RUNTIME)
@interface Height{}

@BindingAnnotation
@Target({PARAMETER, METHOD})
@Retention(RUNTIME)
@interface Depth{}

class Box{
    final int width;
    final int height;
    final int depth;
    @Inject
    public Box(@Width final int width, @Height int height, @Depth int depth) {
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
        bindConstant().annotatedWith(Width.class).to(2);
        bindConstant().annotatedWith(Height.class).to(3);
        bindConstant().annotatedWith(Depth.class).to(4);
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
https://github.com/google/guice/wiki/BindingAnnotations

output:
24
 */
