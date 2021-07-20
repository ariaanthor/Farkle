import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;
/**
 * Write a description of class FarkleDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


//all of my GUI stuff

public class FarkleDisplay
{
    private JFrame frame;
    private ActionEvent event;
    private JButton roll = new JButton("Roll");
    private JButton keep = new JButton("Keep");
    private JTextField jplayer = new JTextField("Player");
    private JTextArea jscore = new JTextArea("0");
    private Farkle game;
    ArrayList <JButton> diceButtons = new ArrayList <JButton>();
    ArrayList <Boolean> clicked = new ArrayList <Boolean>();
    /**
     * Constructor for objects of class FarkleDisplay
     */
    public FarkleDisplay()
    {
        // initialise instance variables
        frame = new JFrame("Dice Board"); 
        frame.setSize(2000, 2000);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//getter method for Farkle class
    public ArrayList <JButton> getDiceButtons() {
        return diceButtons;
    }


    /**
     * Method displayGUI
     * Displays the Current roll and the banked dice.
     * @param valArray A parameter
     * @param bankedDiceArr A parameter
     */
    //displays the frame
    public void displayPanel(Farkle thisgame)
    {
        frame.removeAll();
        frame = new JFrame("Dice Board"); 
        frame.setSize(2000, 2000);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = thisgame;
        try {
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    // Clicklistener click= new Clicklistener();
                    keep.setBounds(50, 50, 100, 50);
                    roll.setBounds(50, 150, 100, 50);
                    jplayer.setBounds(50, 250, 100, 50);
                    jscore.setBounds(50, 350, 100, 200);
                    clicked = new ArrayList <Boolean>();

                    keep.setHorizontalTextPosition(AbstractButton.CENTER);
                    keep.setVerticalTextPosition(AbstractButton.BOTTOM);

                    roll.setHorizontalTextPosition(AbstractButton.CENTER);
                    roll.setVerticalTextPosition(AbstractButton.BOTTOM);
                    keep.addActionListener(new ActionListener(){  
                            public void actionPerformed(ActionEvent e){  
                                clickkeep(e);
                            }  
                        });
                    roll.addActionListener(new ActionListener(){  
                            public void actionPerformed(ActionEvent e){  
                                clickroll(e);
                            }  
                        });  

                    frame.add(roll);
                    frame.add(keep);
                    frame.add(jplayer);
                    frame.add(jscore);
                    frame.setLayout(null);
                    frame.setVisible(true);
                }
            }
        )
        ;}
        catch (Exception e)
        {}
    }

    
    //makes it so that I can easily add and remove die from the frame so that i dont need to constantly create new frames
    public void addDies(ArrayList <Integer> valArray)
    {
        System.out.println("Removing dies");
        for (JButton jb : diceButtons)
        {
            frame.remove(jb);
        }
        System.out.println("Adding dies");

        diceButtons = new ArrayList <JButton>();
        int dienum=1;
        int counter = 0;
        int xVal = 300;
        int yVal = 300;
        ActionListener al = new  ActionListener(){
            public void actionPerformed(ActionEvent e){
                wasClickedDice(e);
            }};

        for (Integer i : valArray)
        {
            clicked.add(false);
            if (counter >= 3) {
                counter = 0;
                yVal = 550;
            }
            JButton button = Dice.displaySingleButton(i,dienum++);
            button.setBounds(xVal + (counter*250), yVal, 200, 225);
            button.addActionListener(al);

            frame.add(button);
            diceButtons.add(button);

            counter++;
        
        SwingUtilities.updateComponentTreeUI(frame);

    }
}
    public void clickroll (ActionEvent e){
        int counter = 1;
        String returnStr="";
        for (JButton b : diceButtons) {
            if (b.getText() == "BANKED")
            {
                returnStr += String.valueOf(counter)+" ";
            } else {
            }
            counter++;
        }
        if (returnStr =="") {
            returnStr = "keep";
        }
        game.setDisplayOutput(returnStr);
    }
//if 'keep' button was clicked...
    public void clickkeep (ActionEvent e){
        game.setDisplayOutput("keep");
    }
// if roll button was clicked...
    public void wasClickedDice (ActionEvent e) {
        int counter = 0;
        JButton b = (JButton) e.getSource();
        if (b.getText() == "BANKED")
        {
            b.setText("");
        }
        else {
            b.setText("BANKED");
        }
    }
//these two methods make the scoreboard
    public void setPlayer(String name)
    {
        jplayer.setText(name);
        SwingUtilities.updateComponentTreeUI(frame);

    }
    public void setScore(String allscores)
    {
        jscore.setText(allscores);
        SwingUtilities.updateComponentTreeUI(frame);

    }

}
