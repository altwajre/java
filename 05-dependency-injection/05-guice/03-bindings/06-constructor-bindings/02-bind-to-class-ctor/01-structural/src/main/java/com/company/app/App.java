package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import java.lang.reflect.Constructor;

class Dependency{
}
class Sealed {
    private final Dependency dep;

    public Sealed(Dependency dep) {
        this.dep = dep;
        System.out.println("#Sealed constructor");
    }
}
class MyClass extends Sealed{
    public MyClass(Dependency dep) {
        super(dep);
        System.out.println("#Called MyClass constructor");
    }
}
class SealedModule extends AbstractModule{
    @Override
    protected void configure() {
        //# Bind directly to a constructor of Sealed
        try {
            bind(Sealed.class).toConstructor(MyClass.class.getConstructor(Dependency.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private Constructor<Sealed> sealedConstructor(){
        System.out.println("#SealedModule.sealedConstructor()");
        try{
            //# Resolve the constructor we want to inject
            return Sealed.class.getConstructor(Dependency.class);
        }catch (NoSuchMethodException e){
            System.out.println(e);
            return null;
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new SealedModule()).getInstance(Sealed.class);
    }
}
/*
https://github.com/google/guice/wiki/ToConstructorBindings
Occasionally it's necessary to bind a type to an arbitrary constructor. This comes up when the @Inject annotation
cannot be applied to the target constructor: either because it is a third party class.

output:
#Sealed constructor
#Called MyClass constructor
 */
