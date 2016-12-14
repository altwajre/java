package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

class Bar{
    final String message;
    public Bar(String message) {
        this.message = message;
    }
    @Override
    public String toString(){
        return message;
    }
}
class Foo{
    final Bar bar;
    @Inject
    public Foo(Bar bar) {
        this.bar = bar;
    }
    public Bar getBar(){
        return bar;
    }
}
class ChildModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Bar.class).toInstance(new Bar("from ChildModule"));
    }
}
class ParentModule extends AbstractModule{
    @Override
    protected void configure() {
        install(new ChildModule());
        bind(Foo.class);
    }
}
public class CombineModules {
    public static void main(String... args){
        Injector injector = Guice.createInjector(new ParentModule());
        Foo foo = injector.getInstance(Foo.class);
        System.out.println(foo.getBar());
    }
}
/*
http://blog.muhuk.com/2015/05/28/using_guice_effectively.html#.VxcZ4eYrJTY

Module Hierarchy
Guice module are modular. Just like we organize closely related classes in packages we can organize relevant bindings in
different modules and then connect them either using module hierarchies or during injector creation. This doesn’t
necessarily mean each package should have a module. Too many modules would make your code difficult to follow.

output:
from ChildModule
 */

