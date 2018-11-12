# object oriented programming

https://en.wikipedia.org/wiki/Abstraction_(software_engineering)

- Abstraction
- Encapsulation
- Inheritance

## Polymorphism

https://www.youtube.com/watch?v=qqYOYIVrso0

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
        // Polymorphism - put all subclass objects into Animal list, and then iterator them and call eat() on each animal.
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
