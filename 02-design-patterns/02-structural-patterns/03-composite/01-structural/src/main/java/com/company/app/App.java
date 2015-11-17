package com.company.app;

import java.util.ArrayList;

abstract class Component{
    protected String name;
    public Component(String name){
        this.name = name;
    }
    public abstract void add(Component c);
    public abstract void remove(Component c);
    public abstract void display(int depth);
}
class Composite extends Component{
    private ArrayList<Component> children = new ArrayList<Component>();
    public Composite(String name) {
        super(name);
    }
    @Override
    public void add(Component c) {
        children.add(c);
    }
    @Override
    public void remove(Component c) {
        children.remove(c);
    }
    @Override
    public void display(int depth) {
        System.out.printf(new String(new char[depth]).replace("\0", "-") + name + "\n");
        for(Component component : children){
            component.display(depth + 2);
        }
    }
}
class Leaf extends Component{
    public Leaf(String name) {
        super(name);
    }
    @Override
    public void add(Component c) {
        System.out.println("Cannot add to a leaf");
    }
    @Override
    public void remove(Component c) {
        System.out.println("Cannot remove from a leaf");
    }
    @Override
    public void display(int depth) {
        System.out.printf(new String(new char[depth]).replace("\0", "-") + name + "\n");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Composite root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));

        root.add(comp);
        root.add(new Leaf("Leaf C"));

        Leaf leaf = new Leaf("Leaf D");
        root.add(leaf);
        root.remove(leaf);

        root.display(1);
    }
}
/*
Definition
Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual
objects and compositions of objects uniformly.

output:
-root
---Leaf A
---Leaf B
---Composite X
-----Leaf XA
-----Leaf XB
---Leaf C
 */
