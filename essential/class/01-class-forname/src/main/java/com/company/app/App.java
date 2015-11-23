package com.company.app;

/*
http://www.xyzws.com/javafaq/what-does-classforname-method-do/17

A class to Class.forName(“X”) causes the class named X to be dynamically loaded (at runtime). A call to forName(“X”)
causes the class named to be initialized (i.e., JVM executes all its static block after class loading).
Class.forName(“X”) returns the Class object associated with the “X” class. The returned Class object is not an
instance of the “x” class itself.

Class.forName(“X”) loads the class if it not already loaded. The JVM keeps track of all the classes that have been
previously loaded. This method uses the classloader of the class that invokes it. The “X” is the fully qualified
name of the desired class.

 */
class MyClass{
    MyClass(){
        System.out.println("constructor");
    }
    static {
        System.out.println("MyClass.static block1");
    }
    static {
        System.out.println("MyClass.static block2");

    }
    public void print(){
        System.out.println("MyClass.print()");
    }
}
public class App
{
    public static void main( String[] args )
    {
        try {
            Class c = Class.forName("com.company.app.MyClass"); // execute static blocks after class loading.
            MyClass myClass = (MyClass) c.newInstance(); // Create a new instance of MyClass. Constructor is invoked.
            myClass.print();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("\n*** new a Class has static - static should not invoked");
        MyClass myClass2 = new MyClass();
        myClass2.print();
    }
}
/*
output:
MyClass.static block1
MyClass.static block2
constructor
MyClass.print()

*** new a Class has static - static should not invoked
constructor
MyClass.print()
 */
