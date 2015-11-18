package com.company.app;

interface Iterator{
    boolean hasNext();
    Object next();
}
interface Container{
    Iterator getIterator();
}
class NameRepository implements Container{
    public String names[] = {"Robert", "John", "Julie", "Lora"};
    public Iterator getIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator{
        int index = 0;
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        NameRepository nameRepository = new NameRepository();
        for(Iterator iter = nameRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.format("Name : %s\n", name);
        }
    }
}
/*
Definition
Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

output:
Name : Robert
Name : John
Name : Julie
Name : Lora
 */