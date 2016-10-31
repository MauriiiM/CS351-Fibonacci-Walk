/**
 * Created by mmonsivais on 10/27/16.
 */
public class FibonacciWalk
{
  public static void main(String args[])
  {
    System.out.println("Program started");
    //create threads
    Worker A = new Worker("A");
    A.start();
    Worker B = new Worker("B");
    B.start();

    //run threads
    try
    {
      for (int i = 0; i < 10; i++)
      {
        Thread.sleep(2000);
        synchronized (A)
        {
          System.out.printf("%s(%,d): %,d + %,d = %,d\n", A.NAME, A.step, A.x, A.y, A.z);
        }
        synchronized (B)
        {
          System.out.printf("%s(%,d): %,d + %,d = %,d\n\n", B.NAME, B.step, B.x, B.y, B.z);
        }
      }

    }
    catch (InterruptedException e)
    {
      System.err.println("Sleep interrupted");
    }

    //close threads
    A.closeThread();
    B.closeThread();
    while(true)
    {
      if (!A.isAlive() && !B.isAlive())
      {
        System.out.println("Program Exit");
        System.exit(0);
      }
    }
  }
}

class Worker extends Thread
{
  final String NAME; //Name of thread
  long step = 0; //Steps since start.
  long z = 2; // fib(step)
  long y = 1; // fib(step-1)
  long x = 1; // fib(step-2)
  private volatile boolean running = true;


  Worker(String name)
  {
    NAME = name;

  }

  @Override
  public void run()
  {
    while (running)
    {
      fibonacciSeq();
    }
    System.out.println(NAME + " has exited");
  }

  void closeThread()
  {
    running = false;
  }

  private synchronized void fibonacciSeq()
  {
    step++;
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
    z = y + x;
  }
}
