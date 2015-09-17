package com.company.app;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/*

Provide a lock only if there are no possible deadlocks.

There are several common ways to prevent deadlocks. One of the popular ways is to require a process to declare upfront
what locks it will need. We can then verify if a deadlock would be created by issuing these locks, and we can fail if so

output:
#testNoCycle
true
true
true
getLock(1, 1): lockId=1
getLock(1, 2): lockId=2
getLock(2, 1): lockId=1
getLock(2, 4): lockId=4
getLock(3, 1): lockId=1
getLock(3, 3): lockId=3
#testHasCycle
true
false
true
getLock(1, 1): lockId=1
getLock(1, 2): lockId=2
getLock(2, 1): NullPointerException occurs
getLock(3, 1): lockId=1
getLock(3, 4): lockId=4

 */
public class App 
{
    static class Lock{
        public Lock(int id){
            this.id = id;
        }
        public int id;
        boolean isLocked = false;
        public void lock(){
            synchronized (this) {
                while (isLocked) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
                notify();
                isLocked = false;
            }
        }
    }
    public enum VisitState{ FRESH, VISITING, VISITED };
    static class LockNode {
        public LockNode(int lockId, int maxLocks){
            this.lockId = lockId;
            this.maxLocks = maxLocks;
        }
        public int lockId;
        private Lock lock;
        public int maxLocks;
        public ArrayList<LockNode> children = new ArrayList<LockNode>();
        public void connect(LockNode node){
            children.add(node);
        }
        public void remove(LockNode node){
            children.remove(node);
        }
        public boolean hasCycle(){
            VisitState[] visitStates = new VisitState[maxLocks];
            for(int i = 0; i < maxLocks; i++){
                visitStates[i] = VisitState.FRESH;
            }
            return hasCycle(visitStates);
        }
        public boolean hasCycle(VisitState[] visitStates){
            if(visitStates[lockId] == VisitState.VISITING) return true;
            else if(visitStates[lockId] == VisitState.FRESH){
                visitStates[lockId] = VisitState.VISITING;
                for(LockNode lockNode : children){
                    if(lockNode.hasCycle(visitStates)) return true;
                }
                visitStates[lockId] = VisitState.VISITED;
            }
            return false;
        }
        public Lock getLock(){
            if(lock == null){
                lock = new Lock(lockId);
            }
            return lock;
        }
    }
    static class LockFactory{
        private static LockFactory instance;
        public LockNode[] locks;
        private Hashtable<Integer, LinkedList<LockNode>> lockOrderHashtable;
        private LockFactory(int count){
            locks = new LockNode[count];
            lockOrderHashtable = new Hashtable<Integer, LinkedList<LockNode>>();
            for(int i = 0; i < count; i++){
                locks[i] = new LockNode(i, count);
            }
        }
        public static LockFactory getInstance(){
            return instance;
        }
        public static LockFactory initialize(int count){
            if(instance == null){
                instance = new LockFactory(count);
            }
            return instance;
        }
        public boolean hasCycle(int[] resourcesInOrder){
            LockNode lockNode = locks[resourcesInOrder[0]];
            if(lockNode.hasCycle()){
                return true;
            }
            return false;
        }
        public boolean declare(int ownerId, int[] resourcesInOrder){
            for(int i = 1; i < resourcesInOrder.length; i++){
                LockNode prev = locks[resourcesInOrder[i - 1]];
                LockNode curr = locks[resourcesInOrder[i]];
                prev.connect(curr);
            }
            // if we created a cycle, destroy this resource list and return false
            if(hasCycle(resourcesInOrder)){
                for(int i = 1; i < resourcesInOrder.length; i++){
                    LockNode prev = locks[resourcesInOrder[i - 1]];
                    LockNode curr = locks[resourcesInOrder[i]];
                    prev.remove(curr);
                }
                return false;
            }
            // get the lock, verifying first that the process is really calling the locks in the order
            LinkedList<LockNode> list = new LinkedList<LockNode>();
            for(int i = 0; i < resourcesInOrder.length; i++){
                LockNode resource = locks[resourcesInOrder[i]];
                list.add(resource);
            }
            lockOrderHashtable.put(ownerId, list);
            return true;
        }
        public Lock getLock(int ownerId, int resourceId){
            LinkedList<LockNode> list = lockOrderHashtable.get(ownerId);
            if(list == null) return null;
            LockNode head = list.getFirst();
            if(head.lockId == resourceId){
                list.removeFirst();
                return head.getLock();
            }
            return null;
        }
    }
    public static void main( String[] args )
    {
        testNoCycle();
        testHasCycle();
    }
    private static void testNoCycle() {
        System.out.println("#testNoCycle");
        int[] resourcesInOrder1 = {1,2,3,4};  // Thread A locks 1, 2, 3, 4
        int[] resourcesInOrder2 = {1,4,5};  // Thread B locks 1, 4, 5
        int[] resourcesInOrder3 = {1,3,5};  // Thread C locks 1, 3, 5
        LockFactory.initialize(10);  // create 10 locks (LockNodes)
        LockFactory lockfactory = LockFactory.getInstance();

        int ownerId1 = 1;
        int ownerId2 = 2;
        int ownerId3 = 3;
        System.out.println(lockfactory.declare(ownerId1, resourcesInOrder1));
        System.out.println(lockfactory.declare(ownerId2, resourcesInOrder2));
        System.out.println(lockfactory.declare(ownerId3, resourcesInOrder3));
        System.out.println("getLock(1, 1): lockId="+lockfactory.getLock(1, 1).id);
        System.out.println("getLock(1, 2): lockId="+lockfactory.getLock(1, 2).id);
        System.out.println("getLock(2, 1): lockId="+lockfactory.getLock(2, 1).id);
        System.out.println("getLock(2, 4): lockId="+lockfactory.getLock(2, 4).id);
        System.out.println("getLock(3, 1): lockId="+lockfactory.getLock(3, 1).id);
        System.out.println("getLock(3, 3): lockId="+lockfactory.getLock(3, 3).id);
    }
    private static void testHasCycle() {
        System.out.println("#testHasCycle");
        int[] resourcesInOrder1 = {1,2,3,4};  // Thread A locks 1, 2, 3, 4
        int[] resourcesInOrder2 = {1,5,4,1};  // Thread B locks 1, 5, 4, 1
        int[] resourcesInOrder3 = {1,4,5};  // Thread C locks 1, 4, 5
        LockFactory.initialize(10);  // create 10 locks (LockNodes)
        LockFactory lockfactory = LockFactory.getInstance();

        int ownerId1 = 1;
        int ownerId2 = 2;
        int ownerId3 = 3;
        System.out.println(lockfactory.declare(ownerId1, resourcesInOrder1));
        System.out.println(lockfactory.declare(ownerId2, resourcesInOrder2));
        System.out.println(lockfactory.declare(ownerId3, resourcesInOrder3));
        System.out.println("getLock(1, 1): lockId="+lockfactory.getLock(1, 1).id);
        System.out.println("getLock(1, 2): lockId="+lockfactory.getLock(1, 2).id);
        try{
            System.out.println("getLock(2, 1): lockId="+lockfactory.getLock(2, 1).id);
        }
        catch (NullPointerException e){
            System.out.println("getLock(2, 1): NullPointerException occurs");
        }
        System.out.println("getLock(3, 1): lockId="+lockfactory.getLock(3, 1).id);
        System.out.println("getLock(3, 4): lockId="+lockfactory.getLock(3, 4).id);
    }
}
