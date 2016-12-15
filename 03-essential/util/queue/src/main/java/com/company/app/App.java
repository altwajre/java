package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
output:
after enqueue items to queue; queue=[Tom, Dick, Harry]
after dequeue item(Tom) from queue; queue=[Dick, Harry]

 */
public class App 
{
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        System.out.print("after enqueue items to queue; queue=");
        queue.offer("Tom");
        queue.offer("Dick");
        queue.offer("Harry");
        System.out.println(queue);

        String dequeueItem = queue.poll();
        System.out.print("after dequeue item(" + dequeueItem + ") from queue; queue=");
        System.out.println(queue);
    }
}
