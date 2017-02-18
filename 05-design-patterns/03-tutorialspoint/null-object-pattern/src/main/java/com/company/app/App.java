package com.company.app;

import java.util.Arrays;
import java.util.List;

// https://www.tutorialspoint.com/design_pattern/null_object_pattern.htm
abstract class AbstractCustomer {
    protected String name;
    public abstract boolean isNil();
    public abstract String getName();
}
class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name){
        this.name = name;
    }
    @Override
    public boolean isNil() {
        return false;
    }
    @Override
    public String getName() {
        return name;
    }
}
class NullCustomer extends AbstractCustomer{
    @Override
    public boolean isNil() {
        return false;
    }
    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
class CustomerFactory{
    public static final String[] names = {"Tom", "Dick", "Harry"};
    public static AbstractCustomer getCustomer(String name){
        for(int i = 0; i < names.length; i++){
            if(names[i].equalsIgnoreCase(name)){
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}
public class App
{
    public static void main( String[] args )
    {
        List<String> names = Arrays.asList(new String[]{"Tom", "Will", "Harry", "Bob"});

        names.forEach(name -> System.out.println(CustomerFactory.getCustomer(name).getName()));
    }
}
/*
output:
Tom
Not Available in Customer Database
Harry
Not Available in Customer Database
 */
