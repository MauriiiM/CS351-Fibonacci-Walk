/**
 * Created by mmonsivais on 10/27/16.
 */
public class FibonacciWalk
{
  public static void main(String args[])
  {
    Worker A = new Worker("A");
    A.start();
    Worker B = new Worker("B");
    B.start();


    for (int i = 0; i < 10; i++)
    {
      try
      {
        Thread.sleep(200);
        System.out.println("Sleeping");
      }
      catch (InterruptedException e)
      {
        System.err.println("Sleep interrupted");
      }
      System.out.printf("%s %,d: %,d + %,d = %,d\n", A.NAME, A.step, A.y, A.x, A.z);
      System.out.printf("%s %,d: %,d + %,d = %,d\n\n", B.NAME, B.step, B.y, B.x, B.z);
    }

//    while (A.isAlive() || B.isAlive())
//    {
    A.closeThread();
    B.closeThread();
//    }
    if (!A.isAlive() && !B.isAlive())
    {
      System.out.println("Program Exit");
      System.exit(0);
    }
    else System.out.printf("NOPE");
  }
}

class Worker extends Thread
{
  final String NAME; //Name of thread
  long step = 0; //Steps since start.
  long z; // fib(step)
  long y = 1; // fib(step-1)
  long x = 1; // fib(step-2)
  private boolean running = true;

  Worker(String name)
  {
    NAME = name;
  }

  @Override
  public void run()
  {
    while (running)
    {
      step++;
      z = y + x;
      if (z == 7_540_113_804_746_346_429L)
      {
        x = 1;
        y = 1;
      }
      else
      {
        x = y;
        y = z;
      }
//      System.out.println("jjj");
    }
    System.out.println(NAME + " has exited");
  }

  void closeThread()
  {
    running = false;
  }
}
