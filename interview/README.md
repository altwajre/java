## Passing objects to a method

> http://www.dreamincode.net/forums/topic/236550-passing-objects-to-a-method/

  class Node{
    public int Data;
    public Node Next;
  }
  Node a (aliasing) is the caller variable - foo(a);
  Node b (aliasing) is the callee variable - foo(Object b){}
  Actually, we pass a handle of an object, and in the callee method a new handle created and pointed to the same object.
  Now when more than one handles tied to the same object, it is known as aliasing. For the example below, both "a" and "b" 
  point to the same object "[1]" that is Node{Data=1, Next=new Node{Data=2, ...}}.
    a   b
     \ /    
     [1] -> [2] -> [3] -> null

  When using "b" to change data "b.Data = 11", it will affect "a" which means "a.Data" is also "11"
    a   b
     \ /    
     [11] -> [2] -> [3] -> null

  When using "b" to change the pointer to Next [2] "b = b.Next", 
  it won't affect "a" because "a" is still pointing at the [1]
    a      b
     \      \
     [1] -> [2] -> [3] -> null

  So following code is good because node variable inside of callee printLinkedList() does not affect caller variable.
    void printLinkedList(Node node){
        while(node != null){
            System.out.println(node.Data);
            node = node.Next;
        }
    }
