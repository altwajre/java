package gof.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/*
Definition
Use sharing to support large numbers of fine-grained objects efficiently.
 */
class Char {  // Flyweight
  protected char symbol;
  protected Integer width;
  protected Integer height;
  protected Integer ascent;
  protected Integer descent;
  public void display(Integer pointSize){
    System.out.format("%s (pointsize %s)\n", this.symbol, pointSize);
  }
}
class CharA extends Char {  // Concrete Flyweight
  public CharA(){
    this.symbol = 'A';
    this.height = 100;
    this.width = 120;
    this.ascent = 70;
    this.descent = 0;
  }
}
class CharB extends Char {  // Concrete Flyweight
  public CharB(){
    this.symbol = 'B';
    this.height = 100;
    this.width = 140;
    this.ascent = 72;
    this.descent = 0;
  }
}
class CharZ extends Char {  // Concrete Flyweight
  public CharZ(){
    this.symbol = 'Z';
    this.height = 100;
    this.width = 100;
    this.ascent = 68;
    this.descent = 0;
  }
}
class CharFactory{  // FlyweightFactory
  private Map<Character, Char> characters = new HashMap<Character, Char>();
  public Char getChar(Character key){
    // Uses "lazy initialization"
    Char character = null;
    if(characters.containsKey(key)){
      character = characters.get(key);
    }
    else{
      switch (key){
        case 'A': character = new CharA(); break;
        case 'B': character = new CharB(); break;
        //...
        case 'Z': character = new CharZ(); break;
      }
      characters.put(key, character);
    }
    return character;
  }
}
public class RealWorld {
  public static void main( String[] args )
  {
    String document = "AAZZBBZB";
    char[] chars = document.toCharArray();
    CharFactory factory = new CharFactory();
    int pointSize = 10;
    for(char c : chars){
      pointSize++;
      Char character = factory.getChar(c);
      character.display(pointSize);
    }
  }
}
/*
A (pointsize 11)
A (pointsize 12)
Z (pointsize 13)
Z (pointsize 14)
B (pointsize 15)
B (pointsize 16)
Z (pointsize 17)
B (pointsize 18)
 */
