package com.company.app;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;

/*
asSubclass is used
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Test{ }

@Test
class MyTest{

}
public class App
{
    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName){
        Class<?> annotationType = null;  // Unbounded type token
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }

    public static void main( String[] args ) throws Exception {
        String className = "com.company.app.MyTest";
        String annotationTypeName = "com.company.app.Test";
        Class<?> klass = Class.forName(className);
        System.out.println(getAnnotation(klass, annotationTypeName));

        asSubclassTest();
    }

    private static void asSubclassTest() {
        System.out.println("#asSubclassTest");
        SupClass supClass = new SupClass();
        SubClass subClass = new SubClass();
        Class supK = supClass.getClass();
        System.out.println(supK);

        Class subK = subClass.getClass();
        System.out.println(subK);

        // represent a subclass of the specified class object
        Class retval = subK.asSubclass(supK);
        System.out.println(retval);
    }
}
class SupClass{}
class SubClass extends SupClass{}
/*
output:
@com.company.app.Test()
#asSubclassTest
class com.company.app.SupClass
class com.company.app.SubClass
class com.company.app.SubClass
 */
