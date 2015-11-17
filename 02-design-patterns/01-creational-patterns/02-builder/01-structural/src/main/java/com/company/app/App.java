package com.company.app;

import java.util.ArrayList;
import java.util.List;

abstract class Builder{
    public abstract void BuildPartA();
    public abstract void BuildPartB();
    public abstract Product GetResult();
}
class ConcreteBuilder1 extends Builder{
    private Product product = new Product();
    @Override
    public void BuildPartA() {
        product.Add("PartA");
    }
    @Override
    public void BuildPartB() {
        product.Add("PartB");
    }
    @Override
    public Product GetResult() {
        return product;
    }
}
class ConcreteBuilder2 extends Builder{
    private Product product = new Product();
    @Override
    public void BuildPartA() {
        product.Add("PartX");
    }
    @Override
    public void BuildPartB() {
        product.Add("PartY");
    }
    @Override
    public Product GetResult() {
        return product;
    }
}
class Product{
    private List<String> parts = new ArrayList<String>();
    public void Add(String part){
        parts.add(part);
    }
    public void Show(){
        System.out.println("\nProduct Parts ------");
        for(String part : parts){
            System.out.println(part);
        }
    }
}
class Director{
    public void Construct(Builder builder){
        builder.BuildPartA();
        builder.BuildPartB();
    }
}
// Builder
public class App
{
    public static void main( String[] args )
    {
        Director director = new Director();
        Builder b1 = new ConcreteBuilder1();
        Builder b2 = new ConcreteBuilder2();
        director.Construct(b1);
        Product p1 = b1.GetResult();
        p1.Show();

        director.Construct(b2);
        Product p2 = b2.GetResult();
        p2.Show();
    }
}
/*
output:
Product Parts ------
PartA
PartB

Product Parts ------
PartX
PartY
 */
