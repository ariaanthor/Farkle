import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;

/**
 * The Dice class is only responsible for managing and displaying the Dice.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dice
{
    private final String partOfLink = "Images/";
    String finalLink;
    static ArrayList<String> links = new ArrayList<String> ();
    static ArrayList<ImageIcon> diceImgs = new ArrayList<ImageIcon> ();
    private static JButton imageButton;
    private static ImageIcon image;
    //getting links into linkArr
    public Dice() {
        links.add(partOfLink+"Die1.png");
        links.add(partOfLink+"Die2.png");
        links.add(partOfLink+"Die3.png");
        links.add(partOfLink+"Die4.png");
        links.add(partOfLink+"Die5.png");
        links.add(partOfLink+"Die6.png");

    }

    //creating a single button that has the dice image and its number (die #1, die #2, die #3, etc)
    public static JButton displaySingleButton(int num,int dienum)
    {

        try
        {
            image = new ImageIcon(ImageIO.read(new File(links.get(num - 1))));
            diceImgs.add(image);
        }
        catch(MalformedURLException mue)
        {
            mue.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        imageButton = new JButton(Integer.toString(dienum));
        imageButton.setIcon(image);
        imageButton.setHorizontalTextPosition(AbstractButton.CENTER);
        imageButton.setVerticalTextPosition(AbstractButton.BOTTOM);

        return(imageButton);
    }
    //returns a random number from 1-6 (basically rolling the die)
    public int getValueOfDice() {
        int num = (int) ((Math.random() * 6) + 1);
        return(num);
    }

}
