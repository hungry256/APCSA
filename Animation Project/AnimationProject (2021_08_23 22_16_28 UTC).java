//AnimationProject
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class AnimationProject
{  
  public static void main (String args[])
  {
    JFrame frame = new JFrame();
    frame.setSize(600,300);//creating the size of the frame
    frame.add(new AnimationProjectPanel());
//    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Allows user to close JFrame 
    frame.setVisible(true);//allows the user to see it
  }
}
