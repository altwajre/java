## Thread Signaling - wait() and notify()

http://stackoverflow.com/questions/13249835/java-does-wait-release-lock-from-synchronized-block
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

Threads acquires the intrinsic lock when it enters a synchronized method. Thread inside the synchronized method is set
as the owner of the lock and is in RUNNABLE state. Any thread that attempts to enter the locked method becomes BLOCKED.

When Thread calls wait it releases the current object lock and than goes to WAITING state.

When some other thread calls notify or notifyAll on that same object the first thread changes state from WAITING to
BLOCKED. Notified thread does NOT automatically reacquire the lock or become RUNNABLE, in fact it must fight for the
lock with all other blocked threads.

WAITING and BLOCKED states both prevent thread from running, but they are different.

WAITING threads must be explicitly transformed to BLOCKED threads by a notify from some other thread.

WAITING never goes directly to RUNNABLE.

When RUNNABLE thread releases the lock (by leaving monitor or by waiting) one of BLOCKED threads automatically takes
takes its place.

Example:

```
    static class Lock{
        // make sure lock() occurs before unlock(), and unlock() is able to unlock the lock
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                // must get lock before entering here
                while (isLocked){
                    try {
                        wait();  // releases lock
                        // must regain the lock to reentering here
                    } catch (InterruptedException e) { }
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
```