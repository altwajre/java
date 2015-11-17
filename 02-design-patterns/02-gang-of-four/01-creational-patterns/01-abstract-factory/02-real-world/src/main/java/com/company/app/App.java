package com.company.app;

abstract class ContinentFactory{
    public abstract Herbivore CreateHerbivore();
    public abstract Carnivore CreateCarnivore();
}
class AfricaFactory extends ContinentFactory{
    @Override
    public Herbivore CreateHerbivore() {
        return new Wildebeest();
    }
    @Override
    public Carnivore CreateCarnivore() {
        return new Lion();
    }
}
class AmericaFactory extends ContinentFactory{
    @Override
    public Herbivore CreateHerbivore() {
        return new Bison();
    }
    @Override
    public Carnivore CreateCarnivore() {
        return new Wolf();
    }
}
abstract class Herbivore{}
abstract class Carnivore{
    public abstract void Eat(Herbivore herbivore);
}
class Wildebeest extends Herbivore{}
class Lion extends Carnivore{
    @Override
    public void Eat(Herbivore herbivore) {
        System.out.println(this.getClass().getSimpleName() + " eats " + herbivore.getClass().getSimpleName());
    }
}
// The 'ProductA2' class
class Bison extends Herbivore{}
// the 'ProductB2' class
class Wolf extends Carnivore{
    @Override
    public void Eat(Herbivore herbivore) {
        System.out.println(this.getClass().getSimpleName() + " eats " + herbivore.getClass().getSimpleName());
    }
}
class AnimalWorld{
    private Herbivore herbivore;
    private Carnivore carnivore;
    public AnimalWorld(ContinentFactory factory){
        this.herbivore = factory.CreateHerbivore();
        this.carnivore = factory.CreateCarnivore();
    }
    public void RunFoodChain(){
        carnivore.Eat(herbivore);
    }
}
// Abstract Factory
public class App
{
    public static void main( String[] args )
    {
        ContinentFactory africa = new AfricaFactory();
        AnimalWorld world = new AnimalWorld(africa);
        world.RunFoodChain();
        ContinentFactory america = new AmericaFactory();
        world = new AnimalWorld(america);
        world.RunFoodChain();
    }
}
/*
output:
Lion eats Wildebeest
Wolf eats Bison
 */
