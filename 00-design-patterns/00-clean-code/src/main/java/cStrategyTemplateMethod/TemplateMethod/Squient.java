package cStrategyTemplateMethod.TemplateMethod;

abstract class Application {
  private boolean done = false;
  protected abstract void initialize();
  protected abstract void idle();
  protected abstract void cleanup();

  protected void done() {
    done = true;
  }

  public void run() {
    initialize();
    while(!done)
      idle();
    cleanup();
  }
}

public class Squient extends Application {
  private int n;

  public static void main(String[] args) {
    new Squient().run();
  }

  @Override
  protected void initialize() {
    System.out.println("initialize: The Squares of the first 3 Integers");
    n = 1;
  }

  @Override
  protected void idle() {
    System.out.printf("%3d  %3d\n", n, n * n);
    if(++n > 3)
      done();
  }

  @Override
  protected void cleanup() {
    System.out.println("cleanup: ...\n");
  }
}
/*
initialize: The Squares of the first 3 Integers
  1    1
  2    4
  3    9
cleanup: ...
 */

