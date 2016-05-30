package com.company.app;

import com.google.inject.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface Camera{}

class SimpleCamera implements Camera{}
class BasementCamera implements Camera{}

@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@interface Basement{}

@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@interface Penthouse{}

@Singleton // if you use directly annotate MasterTerminal as a singleton, you can skip an explicit binding in Module
class MasterTerminal{}

class BasementTerminal extends MasterTerminal{}

class BuildingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Camera.class).annotatedWith(Basement.class).to(BasementCamera.class);
        bind(Camera.class).annotatedWith(Penthouse.class).to(SimpleCamera.class);
        bind(MasterTerminal.class).in(Singleton.class); // bind MasterTerminal to Singleton
        bind(MasterTerminal.class).annotatedWith(Basement.class).to(BasementTerminal.class).in(Singleton.class);
    }
}

class BasementFloor{
    public final Camera camera1;
    public final Camera camera2;

    @Inject // BasementFloor is wired with two separate instances of no-scoped key [Camera, @Basement]
    public BasementFloor(@Basement Camera camera1, @Basement Camera camera2) {
        this.camera1 = camera1;
        this.camera2 = camera2;
    }
}

class Building{
    final Camera camera1;
    final Camera camera2;
    final Camera camera3;
    final Camera camera4;

    Building(@Basement Camera camera1, @Basement Camera camera2,
             @Penthouse Camera camera3, @Penthouse Camera camera4) {
        this.camera1 = camera1;
        this.camera2 = camera2;
        this.camera3 = camera3;
        this.camera4 = camera4;
    }
}

class ControlRoom{
    final MasterTerminal terminal1;
    final MasterTerminal terminal2;
    final MasterTerminal terminal3;
    final MasterTerminal terminal4;

    // terminal1 and terminal2 share the same instance of MasterTerminal
    // terminal3 and terminal4 share the same instance of MasterTerminal
    public ControlRoom(MasterTerminal terminal1, MasterTerminal terminal2,
                @Basement MasterTerminal terminal3, @Basement MasterTerminal terminal4) {
        this.terminal1 = terminal1;
        this.terminal2 = terminal2;
        this.terminal3 = terminal3;
        this.terminal4 = terminal4;
    }
}

public class App {
    public static void main(String... args){
        Injector injector = Guice.createInjector(new BuildingModule());
        BasementFloor basementFloor = injector.getInstance(BasementFloor.class);
        System.out.println(basementFloor.camera1.getClass().getSimpleName());
    }
}

/*
Singleton
All security camera instances share the same instance of MasterTerminal.

 */
