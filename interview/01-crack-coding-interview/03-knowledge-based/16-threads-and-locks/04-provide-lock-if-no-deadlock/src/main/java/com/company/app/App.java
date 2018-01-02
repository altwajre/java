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
class Lock{
    public Lock(int id){
        this.id = id;
    }
    public int id;
    private boolean isLocked = false;
    public void lock(){
        synchronized (this){
            while(isLocked){
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
enum VisitState {
    FRESH, VISITING, VISITED
};
class LockNode {
    private ArrayList<LockNode> children;
    public int lockId;
    private Lock lock;
    private int maxLocks;

    public LockNode(int id, int max) {
        lockId = id;
        children = new ArrayList<LockNode>();
        maxLocks = max;
    }

    /* Join "this" to "node", checking to make sure that it doesn't create a cycle */
    public void joinTo(LockNode node) {
        children.add(node);
    }

    public void remove(LockNode node) {
        children.remove(node);
    }

    /* Check for a cycle by doing a depth-first-search. */
    public boolean hasCycle(Hashtable<Integer, Boolean> touchedNodes) {
        VisitState[] visited = new VisitState[maxLocks];
        for (int i = 0; i < maxLocks; i++) {
            visited[i] = VisitState.FRESH;
        }
        return hasCycle(visited, touchedNodes);
    }

    private boolean hasCycle(VisitState[] visited, Hashtable<Integer, Boolean> touchedNodes) {
        if (touchedNodes.containsKey(lockId)) {
            touchedNodes.put(lockId, true);
        }

        if (visited[lockId] == VisitState.VISITING) {
            return true;
        } else if (visited[lockId] == VisitState.FRESH) {
            visited[lockId] = VisitState.VISITING;
            for (LockNode n : children) {
                if (n.hasCycle(visited, touchedNodes)) {
                    return true;
                }
            }
            visited[lockId] = VisitState.VISITED;
        }
        return false;
    }

    public Lock getLock() {
        if (lock == null) {
            lock = new Lock(lockId);
        }
        return lock;
    }

    public int getId() {
        return lockId;
    }
}
class LockFactory {
    private static LockFactory instance;

    private int numberOfLocks = 5; /* default */
    private LockNode[] locks;

    /* Maps from a process or owner to the order that the owner claimed it would call the locks in */
    private Hashtable<Integer, LinkedList<LockNode>> lockOrder;

    private LockFactory(int count) {
        numberOfLocks = count;
        locks = new LockNode[numberOfLocks];
        lockOrder = new Hashtable<Integer, LinkedList<LockNode>>();
        for (int i = 0; i < numberOfLocks; i++) {
            locks[i] = new LockNode(i, count);
        }
    }

    public static LockFactory getInstance() {
        return instance;
    }

    public static LockFactory initialize(int count) {
        if (instance == null) {
            instance = new LockFactory(count);
        }
        return instance;
    }

    public boolean hasCycle(Hashtable<Integer, Boolean> touchedNodes, int[] resourcesInOrder) {
		/* check for a cycle */
//            for (int resource : resourcesInOrder) {
//                if (touchedNodes.get(resource) == false) {
//                    LockNode n = locks[resource];
//                    if (n.hasCycle(touchedNodes)) {
//                        return true;
//                    }
//                }
//            }

        // we just need to use the first node to check hasCycle
        LockNode n = locks[resourcesInOrder[0]];
        if (n.hasCycle(touchedNodes)) {
            return true;
        }
        return false;
    }

    /* To prevent deadlocks, force the processes to declare upfront what order they will
     * need the locks in. Verify that this order does not create a deadlock (a cycle in a directed graph)
     */
    public boolean declare(int ownerId, int[] resourcesInOrder) {
        Hashtable<Integer, Boolean> touchedNodes = new Hashtable<Integer, Boolean>();

		/* add nodes to graph */
        int index = 1;
        touchedNodes.put(resourcesInOrder[0], false);
        for (index = 1; index < resourcesInOrder.length; index++) {
            LockNode prev = locks[resourcesInOrder[index - 1]];
            LockNode curr = locks[resourcesInOrder[index]];
            prev.joinTo(curr);
            touchedNodes.put(resourcesInOrder[index], false);
        }

		/* if we created a cycle, destroy this resource list and return false */
        if (hasCycle(touchedNodes, resourcesInOrder)) {
            for (int j = 1; j < resourcesInOrder.length; j++) {
                LockNode p = locks[resourcesInOrder[j - 1]];
                LockNode c = locks[resourcesInOrder[j]];
                p.remove(c);
            }
            return false;
        }

		/* No cycles detected. Save the order that was declared, so that we can verify that the
		 * process is really calling the locks in the order it said it would. */
        LinkedList<LockNode> list = new LinkedList<LockNode>();
        for (int i = 0; i < resourcesInOrder.length; i++) {
            LockNode resource = locks[resourcesInOrder[i]];
            list.add(resource);
        }
        lockOrder.put(ownerId, list);

        return true;
    }

    /* Get the lock, verifying first that the process is really calling the locks in the order
     * it said it would. */
    public Lock getLock(int ownerId, int resourceID) {
        LinkedList<LockNode> list = lockOrder.get(ownerId);
        if (list == null) {
            return null;
        }

        LockNode head = list.getFirst();
        if (head.getId() == resourceID) {
            list.removeFirst();
            return head.getLock();
        }
        return null;
    }
}
public class App
{
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
/*
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
