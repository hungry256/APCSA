//SortingAnimationPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
public class SortingAnimationPanel extends JPanel
{
  private int [] arr = new int [100];//creating a new array with 100 empty spaces that will store all of the heights
  private int [] arrX = new int [100];//stores the x values for each "bar"
  private JButton SS, BS, IS,Random, QS;
  private int [] copy = new int [100];
  private int oriX, oriY, width, height,lowest,replacement,counter, number, placement;
  private Timer timer = null;
  private int p=0;
  ArrayList<Integer> t =new ArrayList<Integer>();//creating a new array list
  ArrayList<Integer> iArray =new ArrayList<Integer>();
  ArrayList<Integer> z =new ArrayList<Integer>();
  ArrayList<Integer> jArray =new ArrayList<Integer>();
  public SortingAnimationPanel()
  {
    oriX = 25;
    oriY = 350;
    width = 3;
    
    SS = new JButton ("Selection");
    BS = new JButton ("Bubble");
    IS = new JButton ("Insertion");
    QS = new JButton("Quick");
    Random = new JButton("Randomize");
    
    add(SS);
    add(BS);
    add(IS);
    add(QS);
    add(Random);
    
    SS.addActionListener (new ButtonListener());//Waiting for the button to be pressed.  Creating a new button listener
    Random.addActionListener (new ButtonListener1());
    BS.addActionListener (new ButtonListener2());
    IS.addActionListener (new ButtonListener3());
    QS.addActionListener (new ButtonListener4());
    
    timer = new Timer(30, new ActionListener(){public void actionPerformed(ActionEvent e){}});
    for (int i = 0; i < 100; i++)
    {
      oriX += 6;
      height = (int)(Math.random()*300 + 1);//generating random heights and placing them in the array
      arr[i] = height;
      arrX[i] = oriX;
    }
  }
  public void SelectionSort(int []arr)
  {
    counter = 0;
    lowest = arr[0];
    number= 0;
    timer = new Timer(30, new ActionListener(){//timer for every 30 millisecond.  Timer acts as the outer for loop
      public void actionPerformed(ActionEvent e) 
      {
        if(counter<arr.length)
        {
          for (int h = number; h<100 ;h++)
          {
            if(arr[h] < lowest)//lowest is the first number in the array.  As we run through the array, it takes the smallest value
            {
              lowest = arr[h];
              replacement = h;//to record the index that we find the lowesr value
            }
          }
          if(counter == arr.length-1)
          {
            timer.stop();//when it is done sorting, it stops the timer
          }
          arr[replacement]= arr[number];//swaping the values so that the original value is at the index at where we found the lowest number
          arr[number] = lowest;//setting the lowest number to the index we are on effectively finding the next smallest number
          number++;
          repaint();
          if(number <100)
          {
            lowest = arr[number];
          }
          counter++;
        }
      }
    });
    timer.start();
  }
  public void BubbleSort(int [] arr)
  {
    counter = 0;
    number= 0;
    timer = new Timer(30, new ActionListener(){
      public void actionPerformed(ActionEvent e) 
      {
        number = 0;
        for(int u = number; u<99;u++)
        {
          if(arr[u]>arr[u+1])//if the value to the right of the index we are on ,u, is less than that of the index we are on, then they should be swapped 
          {
            int y = arr[u];
            arr[u] = arr[u+1];
            arr[u+1] = y;
            repaint();
          }
        }
        if(counter == arr.length-1)
        {
          timer.stop();
        }
        counter++;
      }
    });
    timer.start();
  }
  public void InsertionSort(int [] arr)
  {
    int start = 0;
    number= 1;//start at 1 instead of 0 because you compare elements in this sort to the ones to the left of the index
    timer = new Timer(30, new ActionListener(){
      public void actionPerformed(ActionEvent e) 
      {
        int x = number;
        if(x <100)
        {
          placement = arr[number-1];
          while(x>0 && (arr[x]<arr[x-1]))//find the place where the value at the index is no longer less than the value of the index to the left
          {
            int y = arr[x];
            arr[x] = arr[x-1];
            arr[x-1] = y;
            x--;
          }
          repaint();
          if(number == arr.length-1)
          {
            timer.stop();
          }
          number++;
        }
      }
    });
    timer.start();
  }
  public void QuickSort(int []arr)//storing each of the moves in an array and then copying ti over so that the moves are animated
  {
    for(int x = 0; x< arr.length; x++)
    {
      copy[x] = arr[x];//copy the original array over
    }
    doQuickSort(copy, 0, arr.length -1);//call the do quick sort method
    
    timer = new Timer(30, new ActionListener(){//one for loop to prevent the program from not being able to repaint
      public void actionPerformed(ActionEvent e) 
      {
        arr[t.get(p)]= iArray.get(p);//set the infor in the array list to the original array
        arr[z.get(p)]= jArray.get(p);
        p++;//increment the variable to go down the for loop
        repaint();
        if(p == t.size())
        {
          timer.stop();
        }
      }
    });
    timer.start();
  }
  
  private void doQuickSort(int [] copy, int start, int end)//splits the array in half
    //sorts it so that the values that are less than the middle are going to be on the 
    //left of the number, and those that ar greater on the right
  {
    if(start<end)
    {
      int middle = partition(copy, start, end);
      doQuickSort(copy, start, middle);
      doQuickSort(copy, middle +1, end);
    }
  }
  private int partition(int []copy,int s, int e)//i and j move towards each other.  It
    //ends when the two indices pass each other.  Determines the location of the pivot value
  {
    int pivot = copy[s];
    int i = s-1;
    int j  = e +1;
    while(true)
    {
      i = i+1;
      while(copy[i]<pivot)
      {
        i = i+1;
      }
      j = j -1;
      while(copy[j] >pivot)
      {
        j = j-1;
      }
      if(i<j)
      {
        int temp = copy[i];
        t.add(i);//add the indices and values that are being swapped
        z.add(j);
        copy[i] = copy[j];
        iArray.add(copy[j]);
        copy[j] = temp;
        jArray.add(temp);
      }
      else
      {
        return j;
      }  
    }
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    for (int u = 0; u<100;u++)
    {
      g.fillRect(arrX[u], (oriY - arr[u]), width, arr[u]);//for each item in the array, we fill the rectangle.  
      //We use oriY - arr[u] so that the bars start on the bottom of the screen and not the top
    }
  }
  private class ButtonListener implements ActionListener//when the button is pressed
  {
    public void actionPerformed (ActionEvent event)
    {
      if(!timer.isRunning())
      {
        SelectionSort(arr);//when the button for each sort is pressed, it calls the corrosponding method
      }
    }
  }
  private class ButtonListener1 implements ActionListener
  {
    public void actionPerformed (ActionEvent event)
    {
      if(!timer.isRunning())
      {
        reset();//when the user hits the randomize button, all my varibles are reset to the default values
      }
    }
  }
  private class ButtonListener2 implements ActionListener
  {
    public void actionPerformed (ActionEvent event)
    {
      if(!timer.isRunning())//checks if there is a timer running
      {
        BubbleSort(arr);//when the button for each sort is pressed, it calls the corrosponding method
      }
    }
  }
  private class ButtonListener3 implements ActionListener
  {
    public void actionPerformed (ActionEvent event)
    {
      if(!timer.isRunning())
      {
        InsertionSort(arr);//when the button for each sort is pressed, it calls the corrosponding method
      }
    }
  }
  private class ButtonListener4 implements ActionListener
  {
    public void actionPerformed (ActionEvent event)
    {
      if(!timer.isRunning())
      {
        QuickSort(arr);//when the button for each sort is pressed, it calls the corrosponding method
      }
    }
  }
  
  public void reset()
  {
    oriX = 25;
    oriY = 350;
    width = 3;
    for (int i = 0; i < 100; i++)
    {
      oriX += 6;
      height = (int)(Math.random()*300 + 1);
      arr[i] = height;
      arrX[i] = oriX;
      repaint();
    }
  }
}