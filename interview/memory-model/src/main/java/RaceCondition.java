/*
Race condition:
1, both threads execution is interleaved
2, count is 9 instead of 10 after 10 threads called increase()

Solution:
We can use synchronized or lock to block the critical section, only one thread can access at a time.
 */
class Counter { // shared data
  int count = 0;

  public void increase() {
    try {
      Thread.sleep(100);  // add sleep(100) to make it easy to reproduce the race condition
    } catch (InterruptedException e) {
    }
    count++;
  }
}

public class RaceCondition {
  public static void main(String[] args) {
    final Counter counter = new Counter();

    for (int i = 0; i < 10; i++) {
      new Thread("Thread_" + i) {
        public void run() {
          counter.increase();
          System.out.println(Thread.currentThread().getName() + " - count: " + counter.count);
        }
      }.start();
    }

    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("counter.count: " + counter.count);
  }
}
/*
Thread_5 - count: 8
Thread_2 - count: 9
Thread_6 - count: 9
Thread_4 - count: 8
Thread_7 - count: 8
Thread_0 - count: 9
Thread_3 - count: 8
Thread_1 - count: 9
Thread_8 - count: 8
Thread_9 - count: 9
counter.count: 9
 */
