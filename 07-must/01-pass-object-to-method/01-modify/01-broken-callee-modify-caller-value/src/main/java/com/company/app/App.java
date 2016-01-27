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
    BROKEN: When using "callee" to change data ".data=11", it will affect "caller" which means "caller.Data" is "11".
caller  callee
     \ /
     [11] -> [2] -> [3] -> null
     */
    static void modify(Node callee){ // Node callee (aliasing) is the callee variable
        callee.Data = 11;
    }
    public static void main( String[] args )
    {
        Node caller = createLinkedList(new int[]{1,2,3});
        printLinkedList(caller);

        modify(caller); // Node caller (aliasing) is the caller variable

        System.out.println("BROKEN: modify object value inside callee method affect caller a.Data=" + caller.Data);
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
BROKEN: modify object value inside callee method affect caller a.Data=11
11->2->3->
 */
