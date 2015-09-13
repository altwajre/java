package com.company.app;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

output:
true
false
true
java.util.concurrent.locks.ReentrantLock@7440e464[Unlocked]
java.util.concurrent.locks.ReentrantLock@49476842[Unlocked]
null

 */
public class App 
{
    static class LockNode{
        public enum VisitState{ FRESH, VISITING, VISITED };
        private ArrayList<LockNode> children;
        private int lockId;
        private Lock lock;
        private int maxLocks;
        public LockNode(int id, int max){
            lockId = id;
            children = new ArrayList<LockNode>();
            maxLocks = max;
        }
        // Join "this" to "node", checking to make sure that it doesn't create a cycle
        public void joinTo(LockNode node){ children.add(node); }
        public void remove(LockNode node){ children.remove(node); }
        public boolean hasCycle(Hashtable<Integer, Boolean> touchedNodes){
            VisitState[] visited = new VisitState[maxLocks];
            for(int i = 0; i < maxLocks; i++){ visited[i] = VisitState.FRESH; }
            return hasCycle(visited, touchedNodes);
        }
        private boolean hasCycle(VisitState[] visited, Hashtable<Integer, Boolean> touchedNodes){
            if(touchedNodes.containsKey(lockId)){ touchedNodes.put(lockId, true); }
            if(visited[lockId] == VisitState.VISITING){ return true; }
            else if(visited[lockId] == VisitState.FRESH){
                visited[lockId] = VisitState.VISITING;
                for(LockNode n : children){
                    if(n.hasCycle(visited, touchedNodes)){ return true; }
                }
                visited[lockId] = VisitState.VISITED;
            }
            return false;
        }
        public Lock getLock(){
            if(lock == null){ lock = new ReentrantLock(); }
            return lock;
        }
        public int getId(){ return lockId; }
    }
    static class LockFactory{
        private static LockFactory instance;
        private int numberOfLocks = 5; // default
        private LockNode[] locks;
        // Maps from a process or owner to the order that the owner claimed it would call the locks in
        private Hashtable<Integer, LinkedList<LockNode>> lockOrder;
        private LockFactory(int count){
            numberOfLocks = count;
            locks = new LockNode[numberOfLocks];
            lockOrder = new Hashtable<Integer, LinkedList<LockNode>>();
            for(int i = 0; i < numberOfLocks; i++){ locks[i] = new LockNode(i, count); }
        }
        public static LockFactory getInstance(){ return instance; }
        public static LockFactory initialize(int count){
            if(instance == null){ instance = new LockFactory(count); }
            return instance;
        }
        public boolean hasCycle(Hashtable<Integer, Boolean> touchedNodes, int[] resourcesInOrder){
            for(int resource : resourcesInOrder){ // check for a cycle
                if(touchedNodes.get(resource) == false){
                    LockNode n = locks[resource];
                    if(n.hasCycle(touchedNodes)){ return true; }
                }
            }
            return false;
        }
        // To prevent deadlocks, force the processes to declare upfront what order they will
        // need the locks in. Verify that this order does not create a deadlock (a cycle in a directed graph)
        public boolean declare(int ownerId, int[] resourcesInOrder){
            Hashtable<Integer, Boolean> touchedNodes = new Hashtable<Integer, Boolean>();
            // add nodes to graph
            int index = 1;
            touchedNodes.put(resourcesInOrder[0], false);
            for(index = 1; index < resourcesInOrder.length; index++){
                LockNode prev = locks[resourcesInOrder[index - 1]];
                LockNode curr = locks[resourcesInOrder[index]];
                prev.joinTo(curr);
                touchedNodes.put(resourcesInOrder[index], false);
            }
            // if we created a cycle, destroy this resource list and return false
            if(hasCycle(touchedNodes, resourcesInOrder)){
                for(int j = 1; j < resourcesInOrder.length; j++){
                    LockNode p = locks[resourcesInOrder[j - 1]];
                    LockNode c = locks[resourcesInOrder[j]];
                    p.remove(c);
                }
                return false;
            }
            // No cycles detected. Save the order that was declared, so that we can verify that the
            // process is really calling the locks in the order it said it would.
            LinkedList<LockNode> list = new LinkedList<LockNode>();
            for(int i = 0; i < resourcesInOrder.length; i++){
                LockNode resource = locks[resourcesInOrder[i]];
                list.add(resource);
            }
            lockOrder.put(ownerId, list);
            return true;
        }
        public Lock getLock(int ownerId, int resourceID){
            LinkedList<LockNode> list = lockOrder.get(ownerId);
            if(list == null){ return null; }
            LockNode head = list.getFirst();
            if(head.getId() == resourceID){
                list.removeFirst();
                return head.getLock();
            }
            return null;
        }
    }
    public static void main( String[] args )
    {
        int[] res1 = {1,2,3,4};
        int[] res2 = {1,5,4,1};
        int[] res3 = {1,4,5};
        LockFactory.initialize(10);
        LockFactory lf = LockFactory.getInstance();
        System.out.println(lf.declare(1, res1));
        System.out.println(lf.declare(2, res2));
        System.out.println(lf.declare(3, res3));
        System.out.println(lf.getLock(1, 1));
        System.out.println(lf.getLock(1, 2));
        System.out.println(lf.getLock(2, 4));
    }
}
