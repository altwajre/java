# Polymorphism

https://www.youtube.com/watch?v=qqYOYIVrso0

put all subclass objects into Animal list, and then iterator them and call eat() on each animal.

```
abstract class Animal {
    abstract public void eat();
}
class Dog extends Animal {
    public void eat() {
        System.out.println("Dog eat");
    }
}
class Cat extends Animal {
    public void eat() {
        System.out.println("Cat eat");
    }
}
public class App
{
    public static void main( String[] args )
    {
        List<Animal> animals = new ArrayList<>();

        Animal dog1 = new Dog();
        animals.add(dog1);
        Animal cat1 = new Cat();
        animals.add(cat1);
        Animal dog2 = new Dog();
        animals.add(dog2);

        animals.forEach(a -> {
            a.eat();
        });
    }
}
```
