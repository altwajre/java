# data structure

## arrays and strings

### is rotation string

Q: use contains() only once to check if s2 is a rotation of s1

static boolean isRotation(String s1, String s2) {
    int len = s1.length();
    // check that s1 and s2 are equal length and not empty
    if (len == s2.length() && len > 0) {
        String s1s1 = s1 + s1;  // concatenate s1 and s1
        return s1s1.contains(s2);  // use built-in contains() instead of isSubstring()
    }
    return false;
}

### shuffle cards

static void shuffle(int[] cards) {
    for (int i = 0; i < cards.length; i++) {
        Random rand = new Random();
        int randomNum = rand.nextInt(cards.length - 1);
        int temp = cards[i];
        cards[i] = cards[randomNum];
        cards[randomNum] = temp;
    }
}

## Linked lists

### remove dups

Q: remove duplicates from an unordered linked list

static void deleteDups(Node head){
    HashSet<Integer> set = new HashSet<Integer>();

    Node current = head;
    set.add(current.Data);

    while(current.Next != null){
        if(set.add(current.Next.Data)){ // able to add
            current = current.Next;
        }else{// fail to add
            current.Next = current.Next.Next;
        }
    }
}

### nth to last

Q: find the kth to last element of a singly linked list

static Node nthToLast(Node head, int k){
    if(k <= 0) return null;
    Node p1 = head;
    Node p2 = head;
    // Move p2 forward k nodes into the list.
    for(int i = 0; i < k - 1; i++){
        if(p2 == null) return null; // Error check
        p2 = p2.Next;
    }
    System.out.println(p2.Data);
    if(p2 == null) return null;
    // Now, move p1 and p2 at the same speed. When p2 hits the end, p1 will be at the right element.
    while(p2.Next != null){
        p1 = p1.Next;
        p2 = p2.Next;
    }
    return p1;
}

### delete node

Q: delete a node in the middle of a singly linked list, given only access to that node

static boolean deleteNode(Node n){
    if(n == null || n.Next == null){
        return false;
    }
    Node next = n.Next;
    n.Data = next.Data;
    n.Next = next.Next;
    return true;
}

### find loop beginning

Q: given a circular linked list, implement an algorithm which returns the node at the beginning of the loop.

Solution:
1, Create two pointers, FastPointer and SlowPointer
2, Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1 step
3, When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is
4, Move SlowPointer and FastPointer at a rate of one step. Return the new collision point.

static Node findLoopBeginning(Node head){
    Node slow = head;
    Node fast = head;
    // Find meeting point. This will be LOOP_SIZE - k steps into the linked list
    while (fast != null && fast.Next != null){
        slow = slow.Next;
        fast = fast.Next.Next;
        if(slow == fast){  // Collision
            break;
        }
    }
    // Error check - no meeting point, and therefore no loop
    if(fast == null || fast.Next == null){
        return null;
    }
    // Move slow to Head. Keep fast at Meeting Point. Each are k steps from the loop Start.
    // If they move at the same pace, they must meet at Loop Start.
    slow = head;
    while (slow != fast){
        slow = slow.Next;
        fast = fast.Next;
    }
    // Both now point to the start of the loop
    return fast;
}

### Is Palindrome

If we don't know the size of the linked list,
We can iterate through the linked list, using the fast runner / slow runner technique.
At each step in the loop, we push the data from the slow runner onto a stack.
When the fast runner hits the end of the list, the slow runner will reached the middle of linked list.
By this point, the stack will have all the elements from the front of the linked list, but in reverse order.
Now, we simply iterate through the rest of the linked list.
At each iteration, we compare the node data to the top of the stack.

static boolean isPalindrome(Node head){
    Node fast = head;
    Node slow = head;
    Stack<Integer> stack = new Stack<Integer>();

    // Push elements from first half of linked list onto stack. When fast runner (which is moving at 2x speed)
    // reaches the end of the linked list, then we know we're at the middle
    while(fast != null && fast.Next != null){
        stack.push(slow.Data);
        slow = slow.Next;
        fast = fast.Next.Next;
    }
    // Has odd number of elements, so skip the middle element
    if(fast != null){
        slow = slow.Next;
    }
    while(slow != null){
        int top = stack.pop().intValue();
        // If values are different, then it's not a palindrome
        if(top != slow.Data){
            return false;
        }
        slow = slow.Next;
    }
    return true;
}

### reverse linkedlist

static Node reverse(Node head){
    Node previous = null;
    Node next;
    Node current = head;
    while (current != null){
        next = current.Next;
        current.Next = previous;
        previous = current;
        current = next;
    }
    return previous;
}

## stacks and queues

### sort stack

static Stack<Integer> sort(Stack<Integer> s){
    Stack<Integer> r = new Stack<Integer>();
    while(!s.isEmpty()){
        int tmp = s.pop();  // Step 1
        while(!r.isEmpty() && r.peek() > tmp){  // Step 2
            s.push(r.pop());
        }
        r.push(tmp);  // Step 3
    }
    return r;
}

## trees and graphs

### balanced binary tree

Q: a balanced tree is defined to be a tree such that the heights of
the two subtrees of any node never differ by more than one.

public void inOrder(Node node){
    if(node == null) return;
    inOrder(node.left);
    System.out.print(node.data + " ");
    inOrder(node.right);
}
public void preOrder(Node node){
    if(node == null) return;
    System.out.print(node.data + " ");
    preOrder(node.left);
    preOrder(node.right);
}
public void postOrder(Node node){
    if(node == null) return;
    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.data + " ");
}
public void dfs(Node node){  // Depth First Search
    if(node == null) return;
    Stack<Node> stack = new Stack<Node>();
    stack.push(node);
    while(!stack.isEmpty()){
        Node tmp = stack.pop();
        System.out.print(tmp.data + " ");
        if(tmp.left != null) stack.push(tmp.left);
        if(tmp.right != null) stack.push(tmp.right);
    }
}
public void bfs(Node node){  // Breadth First Search
    if(node == null) return;
    Queue<Node> queue = new LinkedList<Node>();
    queue.offer(node);
    while(!queue.isEmpty()){
        Node tmp = queue.poll();
        System.out.print(tmp.data + " ");
        if(tmp.left != null) queue.offer(tmp.left);
        if(tmp.right != null) queue.offer(tmp.right);
    }
}

### two nodes reachable

Q: Given a directed graph, find out whether there is a route between two nodes

A: breath first search

https://en.wikipedia.org/wiki/Directed_graph

// perform a breath first search and see if you hit a node - O([V| + |E|)
static boolean reachable(Graph graph, Node src, Node dest){
    if(src == dest) {return true;}
    for(Node node : graph.nodes.values()){node.isVisited = false;}
    Queue<Node> queue = new LinkedList<Node>();
    src.isVisited = true;
    queue.add(src);
    while(!queue.isEmpty()){
        Node tmp = queue.remove();
        for(Node node : tmp.adjacent){  // go through all neighbor nodes
            if(node == dest) return true;  // reach to the dest node, return true
            if(!node.isVisited){  // add unvisited neighbor nodes to the queue
                node.isVisited = true;
                queue.add(node);
            }
        }
    }
    return false;
}

### binary search tree with minimal height

static Node createMinimalBST(int arr[], int startIndex, int endIndex) {
    if (endIndex < startIndex) return null;
    int mid = (startIndex + endIndex) / 2;
    Node node = new Node(arr[mid]);
    node.left = createMinimalBST(arr, startIndex, mid - 1);
    node.right = createMinimalBST(arr, mid + 1, endIndex);
    return node;
}

### find next node

Q: find the 'next' node of a given node in a binary search tree.
   assume that each node has a link to its parent.

static Node findNext(Node node){
    if(node == null) return null;
    // Found right children -> return leftmost node of right subtree
    if(node.right != null){
        return leftMostChild(node.right);
    }
    else{
        Node current = node;
        Node parent = current.parent;
        // Go up when parent.left != current
        while(parent != null && parent.left != current){
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }
}
static Node leftMostChild(Node node){
    if(node == null) return null;
    while(node.left != null){
        node = node.left;
    }
    return node;
}

### find ancestor of two nodes

Q: find the first common ancestor of two nodes in a binary tree
A: go to a node's parent node, and use bfs to check if it is reachable from parent to the other node

static boolean reachableFromParent(Node ancestor, Node descendant) {
    if (ancestor == descendant) return true;
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(ancestor);
    while (!queue.isEmpty()) {
        Node tmp = queue.remove();
        if (tmp == descendant) return true;
        if (tmp.left != null) queue.add(tmp.left);
        if (tmp.right != null) queue.add(tmp.right);
    }
    return false;
}

static Node commonAncestor(Node x, Node y) {
    while (x != null) {
        if (reachableFromParent(x, y)) return x;
        x = x.parent;
    }
    while (y != null) {
        if (reachableFromParent(y, x)) return y;
        y = y.parent;
    }
    return null;
}

### is subtree

Q: check if T2 is a subtree of T1

static boolean isSubTree(Node r1, Node r2){
    if(r1 == null) return false; // big tree empty & subtree still not found.
    if(r1.data == r2.data){
        if(matchTree(r1, r2)) return true;
    }
    return isSubTree(r1.left, r2) || isSubTree(r1.right, r2);
}
static boolean matchTree(Node r1, Node r2){
    if(r2 == null && r1 == null) return true; // nothing left in the subtree
    // if one, but not both, are empty
    if(r1 == null || r2 == null) return false;
    if(r1.data != r2.data) return false;  // data doesn't match
    return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));
}

## depth of tree

static int depth(Node node){
    if(node == null)
        return 0;
    int leftDepth = depth(node.left);
    int rightDepth = depth(node.right);
    int max = Math.max(leftDepth, rightDepth);
    return 1 + max;
}

### has cycle

public boolean hasCycle(){
    int count = 0;
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(this);
    while(!queue.isEmpty()){
        Node tmp = queue.remove();
        if(tmp.id == this.id){
            count++;
            if(count > 1){
                return true;
            }
        }
        for(Node n : tmp.children){
            queue.add(n);
        }
    }
    return false;
}
