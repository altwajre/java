package com.company.app;

import java.util.Optional;

class Person{
    private String name;
    private Optional<Integer> age;
    public Person(){
        age = Optional.empty();
    }
    public Person(Integer age){
        this.age = Optional.of(Optional.ofNullable(age).orElse(28));
    }
    public String getName(){
        return name;
    }
    public Optional<Integer> getAge(){
        return age;
    }
}
public class App
{
    public static void main( String[] args )
    {
        ageIsEmptyTest();

        ageHasValueTest();

        ageNullTest();
    }

    private static void ageNullTest() {
        System.out.println("# ageNullTest");
        Person person = new Person(null);
        Optional<Integer> optionalAge = person.getAge();
        Integer age = optionalAge.orElse(8);
        System.out.println("new Person(null): age=" + age);
    }

    private static void ageHasValueTest() {
        System.out.println("# ageHasValueTest");
        Person person = new Person(18);
        Optional<Integer> optionalAge = person.getAge();
        if(optionalAge.isPresent()){
            System.out.println("optionalAge is-present: age="+optionalAge.get());
        }
        else{
            Integer age = optionalAge.orElse(8);
            System.out.println("optionalAge is not present: age=" + age);
        }
    }

    private static void ageIsEmptyTest() {
        System.out.println("# ageIsEmptyTest");
        Person emptyCtor = new Person();
        Optional<Integer> optionalAge = emptyCtor.getAge();
        Integer age = optionalAge.orElse(8);
        System.out.println("empty age with orElse(8): age=" + age);
    }
}
/*
output:
# ageIsEmptyTest
empty age with orElse(8): age=8
# ageHasValueTest
optionalAge is-present: age=18
# ageNullTest
new Person(null): age=28
 */
