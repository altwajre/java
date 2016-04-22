package com.company.app;

/*
http://www.dreamincode.net/forums/topic/236550-passing-objects-to-a-method/
 */
class Node{
    public int Data;
    public Node Next;
}
public class App
{
    /*
    We pass a handle of an object, and in the callee method, a new handle created and pointed to the same object.
    When more than one handles tied to the same object, it is known as aliasing.
    For example, both "caller" and "callee" point to the same object "[1]".
caller  callee
     \ /
     [1] -> [2] -> [3] -> null

    To avoid callee modifying caller, make a defensive copy of it.
    Do not use clone method to make the defensive copies because nonfinal class, the clone method is not guaranteed to
    return an object whose class is Node. It could return an instance of an untrusted subclass specifically designed for
    malicious mischief. Such a subclass could record a reference to each instance in a private static list at the time
    of its creation and allow the attacker to access this list. To prevent this sort of attack, do not use the clone
    method to make a defensive copy of a parameter whose type is subclassable by untrusted parties.
caller                             callee
     \                             /
     [1] -> [2] -> [3] -> null   [1]
     */
    static void modify(Node callee){ // Node callee (aliasing) is the callee variable
        Node temp = new Node();  // Defensive copy instead of clone
        temp.Data = callee.Data;
        temp.Data = 11;
    }
    public static void main( String[] args )
    {
        Node caller = createLinkedList(new int[]{1,2,3});
        printLinkedList(caller);

        modify(caller); // Node caller (aliasing) is the caller variable

        System.out.println("Modify object value inside callee method doesn't affect caller caller.Data=" + caller.Data);
        printLinkedList(caller);
    }
    static void printLinkedList(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.Data + "->");
            current = current.Next;
        }
        System.out.println();
    }
    static Node createLinkedList(int[] numbers){
        Node head = new Node();
        Node current = head;
        for(int i = 0; i < numbers.length; i++){
            current.Data = numbers[i];
            if(i < numbers.length - 1){
                current.Next = new Node();
                current = current.Next;
            }
        }
        return head;
    }
}
/*
output:
1->2->3->
Modify object value inside callee method doesn't affect caller caller.Data=1
1->2->3->
 */
