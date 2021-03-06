package gof.creational.abstractfactory;

/*
Definition
Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
 */
interface IHerbivore{ }  // Abstract Product_A

interface ICarnivore{ void Eat(IHerbivore h); } // Abstract Product_B

class Cow implements IHerbivore{ } // Product_A_1

class Lion implements ICarnivore{  // Product_B_1
  public void Eat(IHerbivore h) {
    System.out.println(this.getClass().getSimpleName() + " eats " + h.getClass().getSimpleName());
  }
}

class Horse implements IHerbivore{ }  // Product_A_2

class Wolf implements ICarnivore{  // Product_B_2
  public void Eat(IHerbivore h) {
    System.out.println(this.getClass().getSimpleName() + " eats " + h.getClass().getSimpleName());
  }
}

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
  @Override
  public void RunFoodChain() {
    _carnivore.Eat(_herbivore);
  }
}

public class RealWorld {
  public static void main( String[] args )
  {
    AnimalWorld<?> africa = new AnimalWorld<Africa>(Africa.class);
    africa.RunFoodChain();
    AnimalWorld<?> america = new AnimalWorld<America>(America.class);
    america.RunFoodChain();
  }

}
/*
Lion eats Cow
Wolf eats Horse
 */
