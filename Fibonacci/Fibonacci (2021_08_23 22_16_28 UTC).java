//FibonacciSeq
import java.util.Scanner;
import java.util.ArrayList;
public class Fibonacci
{
  
  public static void main(String[] args)
  {
    Scanner scan = new Scanner (System.in);
    System.out.println("Enter a number");
    int x = scan.nextInt();
    System.out.println("Iterative: " + Fib(x));
    System.out.println("Recursive: " + FibR(x));
  }
  private static int FibR(int x)
  {
    if(x<2)
    {
      return x;
    }
    return FibR(x-1) + FibR(x-2);
  }
  private static int Fib(int x)
  {
    ArrayList <Integer> list = new ArrayList<Integer>();
    list.add(0);
    list.add(1);
      for(int i = 2;i<=x;i++)
      {
        list.add(list.get(i-1) + list.get(i-2));
      }
    return list.get(x);
  }
}