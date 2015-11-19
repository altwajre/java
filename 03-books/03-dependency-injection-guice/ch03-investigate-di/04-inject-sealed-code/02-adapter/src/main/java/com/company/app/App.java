package com.company.app;

import com.google.inject.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

interface Dependency{
}
class Sealed{
    protected final Dependency dep;

    public Sealed(Dependency dep) {
        this.dep = dep;
        System.out.println("#Sealed constructor");
    }
}
class SealedAdapter extends Sealed{
    @Inject
    public SealedAdapter(@My Dependency dep) {
        super(dep);
    }
    public void log(){
        System.out.printf("#%s\n", this.getClass().getSimpleName());
        System.out.printf("#%s\n", dep.getClass().getSimpleName());
    }
}
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface My{}
class MyDependency implements Dependency{
}
class MyModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Dependency.class).annotatedWith(My.class).to(MyDependency.class);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new MyModule()).getInstance(SealedAdapter.class).log();
    }
}
/*
The Adapter pattern allows you to alter the behavior of existing objects by extending them.

output:
#Sealed constructor
#SealedAdapter
#MyDependency
 */
