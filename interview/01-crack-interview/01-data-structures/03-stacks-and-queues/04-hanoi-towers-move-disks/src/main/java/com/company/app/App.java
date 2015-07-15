package com.company.app;

import java.util.Stack;

/*
Q: Towers of Hanoi, you have 3 towers and N disks of different sizes which can slide onto any tower.
   The puzzle starts with disks sorted in ascending order of size from top to bottom.
   1, Only one disk can be moved at a time
   2, A disk is slid off the top of one tower onto the next rod.
   3, A disk can only be placed on top of a larger disk.

 */
public class App 
{
    static class Tower{
        private Stack<Integer> disks;
        private int index;
        public Tower(int i){
            disks = new Stack<Integer>();
            index = i;
        }
        public int getIndex() { return index; }
        public Stack<Integer> getDisks() { return disks; }
        public void add(int d){
            if(!disks.isEmpty() && disks.peek() <= d){
                System.out.println("Error placing disk " + d);
            }
            else{ disks.push(d); }
        }
        public void moveTopTo(Tower destination){
            int top = this.disks.pop();
            destination.add(top);
            System.out.println("Move disk " + top + " from " + getIndex() + " to " + destination.getIndex());
        }
        public void moveDisks(int n, Tower destination, Tower buffer){
            if(n > 0){
                moveDisks(n - 1, buffer, destination);
                moveTopTo(destination);
                buffer.moveDisks(n - 1, destination, this);
            }
        }
    }
    public static void main( String[] args )
    {
        int n = 3;
        Tower[] towers = new Tower[n];
        for(int i = 0; i < 3; i++){
            towers[i] = new Tower(i);
        }
        for(int i = n - 1; i >= 0; i--){
            towers[0].add(i);
        }
        Tower tower0 = towers[0];
        Tower tower1 = towers[1];
        Tower tower2 = towers[2];
        printTowers(tower0, tower1, tower2);

        towers[0].moveDisks(n, towers[2], towers[1]);

        printTowers(tower0, tower1, tower2);
        System.out.println( "done" );
    }

    private static void printTowers(Tower tower0, Tower tower1, Tower tower2) {
        printTower(tower0, "tower0");
        printTower(tower1, "tower1");
        printTower(tower2, "tower2");
    }

    private static void printTower(Tower tower, String name) {
        System.out.println("--Print " + name + " disks--");
        Stack<Integer> disks = tower.getDisks();
        if(disks.isEmpty()){
            System.out.println("Empty");
            return;
        }
        for(int i = disks.size() - 1; i >= 0; i--){
            System.out.println(disks.get(i));
        }
        System.out.println("--done--");
    }
}
