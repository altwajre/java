package com.company.app;

import java.util.ArrayList;

abstract class Aggregate{
    public abstract Iterator CreateIterator();
}
class ConcreteAggregate extends Aggregate{
    private ArrayList items = new ArrayList();
    @Override
    public Iterator CreateIterator() {
        return null;
    }
    public int getCount(){
        return items.size();
    }
    public Object getItem(int index){
        return items.get(index);
    }
    public void setItem(int index, Object value){
        items.add(index, value);
    }
}
abstract class Iterator{
    public abstract Object First();
    public abstract Object Next();
    public abstract boolean IsDone();
    public abstract Object CurrentItem();
}
class ConcreteIteractor extends Iterator{
    private ConcreteAggregate aggregate;
    private int current = 0;
    public ConcreteIteractor(ConcreteAggregate aggregate){
        this.aggregate = aggregate;
    }
    @Override
    public Object First() {
        return aggregate.getItem(0);
    }
    @Override
    public Object Next() {
        Object ret = null;
        if(current < aggregate.getCount() - 1){
            ret = aggregate.getItem(++current);
        }
        return ret;
    }
    @Override
    public boolean IsDone() {
        return current >= aggregate.getCount();
    }

    @Override
    public Object CurrentItem() {
        return aggregate.getItem(current);
    }
}
public class App
{
    public static void main( String[] args )
    {
        ConcreteAggregate a = new ConcreteAggregate();
        a.setItem(0, "Item A");
        a.setItem(1, "Item B");
        a.setItem(2, "Item C");
        a.setItem(3, "Item D");

        ConcreteIteractor i = new ConcreteIteractor(a);
        System.out.println("Iterating over collection:");

        Object item = i.First();
        while(item != null){
            System.out.println(item);
            item = i.Next();
        }
    }
}
/*
Definition
Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

output:
Iterating over collection:
Item A
Item B
Item C
Item D
 */
