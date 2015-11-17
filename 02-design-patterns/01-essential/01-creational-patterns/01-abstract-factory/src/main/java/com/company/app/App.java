package com.company.app;
import static java.lang.System.out;
public class App
{
    static class Cow implements IHerbivore{ } // Product_A_1
    static class Lion implements ICarnivore{  // Product_B_1
        public void Eat(IHerbivore h) {
            out.println(this.getClass().getSimpleName() + " eats " + h.getClass().getSimpleName());
        }
    }
    static class Horse implements IHerbivore{ }  // Product_A_2
    static class Wolf implements ICarnivore{  // Product_B_2
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
    static class Africa implements IContinentFactory{  // Concrete Factory_1
        public IHerbivore CreateHerbivore() { return new Cow(); }
        public ICarnivore CreateCarnivore() { return new Lion(); }
    }
    static class America implements IContinentFactory{  // Concrete Factory_2
        public IHerbivore CreateHerbivore() {
            return new Horse();
        }
        public ICarnivore CreateCarnivore() {
            return new Wolf();
        }
    }
    interface IAnimalWorld{ void RunFoodChain(); }  // Client
    static class AnimalWorld<T extends IContinentFactory> implements IAnimalWorld{  // Client
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
    public static void main( String[] args )
    {
        AnimalWorld<?> africa = new AnimalWorld<Africa>(Africa.class);
        africa.RunFoodChain();
        AnimalWorld<?> america = new AnimalWorld<America>(America.class);
        america.RunFoodChain();
    }
}
