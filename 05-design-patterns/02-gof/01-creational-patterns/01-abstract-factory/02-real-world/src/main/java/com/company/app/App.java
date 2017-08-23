package com.company.app;
import static java.lang.System.out;

class Cow implements IHerbivore{ } // Product_A_1
class Lion implements ICarnivore{  // Product_B_1
    public void Eat(IHerbivore h) {
        out.println(this.getClass().getSimpleName() + " eats " + h.getClass().getSimpleName());
    }
}
class Horse implements IHerbivore{ }  // Product_A_2
class Wolf implements ICarnivore{  // Product_B_2
    public void Eat(IHerbivore h) {
        out.println(this.getClass().getSimpleName() + " eats " + h.getClass().getSimpleName());
    }
}
interface IHerbivore{ }  // Abstract Product_A
interface ICarnivore{ void Eat(IHerbivore h); } // Abstract Product_B
interface IContinentFactory{  // Abstract Factory
    IHerbivore CreateHerbivore();
    ICarnivore CreateCarnivore();
}
class Africa implements IContinentFactory{  // Concrete Factory_1
    public IHerbivore CreateHerbivore() { return new Cow(); }
    public ICarnivore CreateCarnivore() { return new Lion(); }
}
class America implements IContinentFactory{  // Concrete Factory_2
    public IHerbivore CreateHerbivore() {
        return new Horse();
    }
    public ICarnivore CreateCarnivore() {
        return new Wolf();
    }
}
interface IAnimalWorld{ void RunFoodChain(); }  // Client
class AnimalWorld<T extends IContinentFactory> implements IAnimalWorld{  // Client
    private IHerbivore _herbivore;
    private ICarnivore _carnivore;
    private T _factory;
    public AnimalWorld(Class<T> clazz){
        try{
            _factory = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        _carnivore = _factory.CreateCarnivore();
        _herbivore = _factory.CreateHerbivore();
    }
    public void RunFoodChain() {
        _carnivore.Eat(_herbivore);
    }
}
public class App
{
    public static void main( String[] args )
    {
        AnimalWorld<?> africa = new AnimalWorld<Africa>(Africa.class);
        africa.RunFoodChain();
        AnimalWorld<?> america = new AnimalWorld<America>(America.class);
        america.RunFoodChain();
    }
}
/*
Definition
Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

output:
Lion eats Cow
Wolf eats Horse
 */
