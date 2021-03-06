//RecursiveProjectPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
public class RecursiveProjectPanel extends JPanel
{
  String [] choices = {"--","2","3","4","5","6","7","8"};
  JComboBox <String> number = new JComboBox<String>(choices);//creating a new JCombo box(rop down menu)
  private JButton Start, Rerun;
  private Timer timer = null;
  ArrayList<Integer> startPoint =new ArrayList<Integer>();//creating a new array list
  ArrayList<Integer> length =new ArrayList<Integer>();
  ArrayList<Integer> currX =new ArrayList<Integer>();
  ArrayList<Integer> currHeight =new ArrayList<Integer>();
  ArrayList<Integer> height =new ArrayList<Integer>();
  ArrayList<Integer> moves =new ArrayList<Integer>();
  ArrayList<Integer> moveHeight =new ArrayList<Integer>();
  ArrayList<Integer> ring =new ArrayList<Integer>();
  JLabel counters, numRings;
  public RecursiveProjectPanel()
  {      
    add(number);//adding the jcombo box to the panel
    numRings = new JLabel("Choose the amount of rings that you want to add." );//creating a new JLabel
    add(numRings);
    Start = new JButton("Start");
    Start.addActionListener (new ButtonListener());//Waiting for the button to be pressed.  Creating a new button listener
    Rerun = new JButton("Click if you would like to rerun the program. ");
    add(Start);
  }
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.GRAY);//setting the color of the rectangles that are soon to be drawn to gray
    g.fillRect(100, 450, 400, 10);//drawing the stand(the base)
    g.fillRect(180,200,7, 250);//drawing the three pegs
    g.fillRect(300,200,7, 250);
    g.fillRect(420,200,7, 250);
    for (int u = 0; u< length.size(); u++)
    {
      g.setColor(Color.BLACK);//drawing the rings
      g.fillRect(startPoint.get(u), height.get(u), length.get(u), 10);
    }
  }
  private class ButtonListener implements ActionListener//when the button is pressed
  {
    public void actionPerformed (ActionEvent event)
    {
      int val = (int)number.getSelectedIndex();//getting the selected index in the JCombo box
      for(int i= 1; i <= (val+1); i++)
      {
        startPoint.add(0,(118+(7*i)));//starting x value. Each ring is incremented by 7
        currX.add(0,(118+(7*i)));//array list for the current x value 
        height.add(0,(450-(10*i)));//the starting y value for each rings.  Incremented by 10 each time
        currHeight.add(0,(450-(10*i)));
        length.add(0,130-(14*i));//the length of each rings which is incremented by 10 
        repaint();
      }
      solve(val,180, 420, 300,val);//calling the solve method 
      timer = new Timer(500, new ActionListener(){//one for loop to prevent the program from not being able to repaint
        int u = 0;
        public void actionPerformed(ActionEvent e) 
        {
          startPoint.set(ring.get(u),moves.get(u));//array list moves records all of the changes in the starting x value. array list ring is the ring that is being moved 
          height.set(ring.get(u),moveHeight.get(u));//array list moveHeight records all of the changes in the height 
          repaint();
          u++;
          if(u == ring.size())//stops running if u is equal to the max number of rings
          {
            timer.stop();
            number.setVisible(false);//hiding all of the buttons and labelds
            Start.setVisible(false);
            numRings.setVisible(false);
            counters = new JLabel ("Number of moves: " + u);//printing the total number of moves
            add(counters);
            Rerun.addActionListener (new ButtonListener2());//Waiting for the button to be pressed.  Creating a new button listener
            add(Rerun);
            counters.setVisible(true);//Set the buttons and labvels that i want to appear after the program finished running to visible
            Rerun.setVisible(true);
          }
        }
      });
      timer.start();
    }
  }
  private void solve(int rings, int a, int c, int b, int maxRings)//int a is the x value of the first pole, b the second pole ets.  max rings is a constant number of the 
    //maximum rings.  rings is being incremented
  {
    if(rings == 0)
    {
      if(isEmpty(startPoint, c, maxRings)==false)
      {
        currHeight.set(0,(height.get(maxRings-(numRings(startPoint, c, maxRings)))));//maxRings minus the resulting call because the array list height goes from 
        //top to bottom.  numRings returns the number of rings, meaning we need to find the number from the bottom.
        moveHeight.add(currHeight.get(0));
      }
      else
      {
        moveHeight.add(440);//440 is the height if there are no rings
        currHeight.set(0, 440);
      }
      moves.add(currX.get(0) + (c-a));//adding the difference in x value from the peg the ring is moving to and the peg that the rings is coming from to the current x value
      //of the ring
      currX.set(0,(currX.get(0) + (c-a)));
      ring.add(0);
      return;
    }
    solve(rings-1, a, b,c, maxRings);
    if(isEmpty(currX, c, maxRings)==false)
    {
      currHeight.set(rings,(height.get(maxRings-(numRings(startPoint, c, maxRings)))));
      moveHeight.add(currHeight.get(rings));
    }
    else
    {
      moveHeight.add(440);
      currHeight.set(rings, 440);
    }
    moves.add(currX.get(rings) + (c-a));
    currX.set(rings,(currX.get(rings) + (c-a)));
    ring.add(rings);
    solve(rings-1, b, c, a, maxRings);
  }
  private int numRings(ArrayList<Integer> startPoint, int a, int num)//returns the number of rings on the peg
    //num is the total number of rings, a is the x value of the peg
  {
    int counter = 0;
    for(int i = 0; i<= num;i++)
    {
      if ((currX.get(i) < a )&& (currX.get(i) > a - 118))
      {
        counter++;
      }
    }
    return counter;
  }
  private boolean isEmpty(ArrayList<Integer> startPoint, int a, int num)//checks if one peg is empty 
    //num is the total number of rings, a is the x value for the stand
  {
    int start = 0;
    for(int i = 0; i<= num;i++)
    {
      if ((currX.get(i) < a )&& (currX.get(i) > a - 120))//looks to look at the x values to see if there are any that are supposed to be on that peg
      {
        start++;
      }
    }
    if(start == 0)
    {
      return true;
    }
    return false;
  }
  private class ButtonListener2 implements ActionListener//when the second button is pressed which is at the end of the program asking if the ussr wants to rerun
  {
    //if the user wants to rerun
    public void actionPerformed (ActionEvent event)
    {
      Rerun.setVisible(false);//set the move counter and the button that asks the question to not visible
      counters.setVisible(false);
      startPoint.clear();//clearing all of the array lists
      currX.clear();
      height.clear();
      currHeight.clear();
      length.clear();
      moves.clear();
      moveHeight.clear();
      ring.clear();  
      number.setVisible(true);//setting the oriinal buttons and labels to visible
      Start.setVisible(true);
      numRings.setVisible(true);
    }
  }
}


