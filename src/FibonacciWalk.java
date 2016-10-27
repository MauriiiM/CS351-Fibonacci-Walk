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
        Thread.sleep(2000);
        System.out.println("Sleeping");
      }
      catch (InterruptedException e)
      {
        System.err.println("Sleep interrupted");
      }
      System.out.printf("%s: %d + %d = %d", A.NAME, A.y, A.x, A.z);
      System.out.printf("%s: %d + %d = %d", B.NAME, B.y, B.x, B.z);
    }
  }
}

class Worker extends Thread
{
  final String NAME; //Name of thread
  long step = 0; //Steps since start.
  long z; // fib(step)
  long y = 1; // fib(step-1)
  long x = 1; // fib(step-2)

  Worker(String name)
  {
    NAME = name;
  }

  @Override
  public void run()
  {
    step++;
    z = y + x;
  }
}
