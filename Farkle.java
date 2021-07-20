import java.awt.*;
import java.util.*;
//import javafx.util.*;
import javax.swing.*;
/**
 * Write a description of class Farkle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Farkle
{
    ArrayList <Player> players;
    ArrayList <Dice> diceArr;
    TreeMap <Integer, Integer> dupes;
    TreeMap <Integer, Integer> dupeCounts;
    TreeMap <Integer, ArrayList <Integer>> dupeCountVals;
    ArrayList <ArrayList<Integer>> bankedDiceArr;
    ArrayList <Integer> scoreArr;
    Scanner in = new Scanner(System.in);
    private final boolean debug = false;
    JFrame frame = new JFrame();
    FarkleDisplay FDisp = new FarkleDisplay();
    private String displayOutput="";

    public Farkle() {
        scoreArr = new ArrayList<Integer> ();
        bankedDiceArr = new ArrayList<ArrayList<Integer>> ();
        players = new ArrayList <Player> ();
        diceArr = new ArrayList <Dice>();
    }

    public void setDisplayOutput(String disp)
    {
        displayOutput=disp;
    }

    public void setLayout() {
        System.out.println("How many people are playing?: ");
        int pNum = Integer.parseInt(in.nextLine());
        for (int i = 0; i < 6; i++) {
            diceArr.add(new Dice());
        }
        for(int i = 0; i < pNum; i++) {
            System.out.println("Player" + i + ", please enter your name: ");
            String name = in.nextLine();
            Player p = new Player(name);
            players.add(p);
        }
        FDisp.displayPanel(this);

        boolean continueGame = true;
        while (continueGame) {

            for (Player p : players){
                System.out.println(p.getName() + " it is your turn and your current score is " + p.getScore());
                FDisp.setPlayer(p.getName());
                String allscores="";
                for (Player allp : players)
                {
                    allscores += (allp.getName() + ":" + allp.getScore() );
                    allscores +="\n";
                }
                FDisp.setScore(allscores);
                p.incrementScore(startTurn());
                if (p.declareWinner()) {
                    continueGame = false;
                    System.out.println(p.getName() + " you have won with points=" + p.getScore());
                    break;
                }
            }
        }
    }

    /**
     * Method create_dupeDicts
     *
     * @param arrList A parameter consisting of values of the dice. Lenght doesn't matter.
     */
    public void create_dupeDicts (ArrayList <Integer> arrList) {
        dupes = new TreeMap <Integer, Integer> ();
        dupeCounts = new TreeMap <Integer, Integer> (Collections.reverseOrder());
        dupeCountVals = new TreeMap <Integer, ArrayList <Integer>>();
        // Create a TreeMap where where the key is the dice face value and the value is the number of time it is in the array.
        // E.g. for 4 4 4 5 5 6 it will be (4,3) (5,2) (6, 1)
        // E.g. for 1 1 1 4 4 4 it will be (1,3) (4,3)
        for (Integer i : arrList) {
            if (dupes.containsKey(i)) {
                Integer count = dupes.get(i) + 1;
                dupes.replace(i, count);
            } else {
                dupes.put(i, 1);
            }
        }

        // Create a TreeMap where where the key is the the number of time a value appears in the roll and the value is how many of them.
        // E.g. for 4 4 4 5 5 6 it will be (3,1) (2,1) (1, 1)
        // E.g. for 1 1 1 4 4 4 it will be (3,2)
        for (Integer i : dupes.values()) {
            if (dupeCounts.containsKey(i)) {

                Integer count = dupeCounts.get(i) + 1;
                dupeCounts.replace(i, count);
            } else {
                dupeCounts.put(i, 1);
            }
        }

        // Create a TreeMap where where the key is the the number of time a value appears in the roll and the value the list of dice values
        // E.g. for 4 4 4 5 5 6 it will be (3,(4)) (2,(5)) (1, (6))
        // E.g. for 1 1 1 4 4 4 it will be (3,(1,4))
        Set<Integer> dupekeys = dupes.keySet();
        for (Integer i : dupekeys) {
            Integer dupeCount = dupes.get(i);
            if (dupeCountVals.containsKey(dupeCount)) {
                dupeCountVals.get(dupeCount).add(i);
            } else {
                ArrayList <Integer> arl = new ArrayList<Integer>();
                arl.add(i);
                dupeCountVals.put(dupeCount, arl);
            }
        }
    }

    /**
     * Method score
     *
     * @param arrList list of integers wgich represent the dice. It can be any lenght.
     * @return The return value which scores the array representing the dice.
     */
    public int score(ArrayList<Integer> arrList)
    {
        int returnVal = 0;
        int counter = 0;
        Collections.sort(arrList);
        create_dupeDicts(arrList);
        if (debug) {
            System.out.println(arrList);
            System.out.println("dupes:" + dupes);
            System.out.println("dupecounts:" + dupeCounts);
            System.out.println("dupecountvals:"+ dupeCountVals);
        }
        Set<Integer> keys = dupeCounts.keySet();
        Iterator<Integer> dupeCountKeys = keys.iterator();
        int dupeCountKey;

        while (dupeCountKeys.hasNext()) {
            counter += 1;

            dupeCountKey = dupeCountKeys.next();
            if(dupeCountKey == 6) {
                return(3000);
            } else if(dupeCountKey == 5) {
                returnVal = 2000;
            } else if(dupeCountKey == 4) {
                if(dupeCountKeys.hasNext() && dupeCountKeys.next() == 2) {
                    return(1500);
                } else {
                    returnVal = 1000;
                }
            } else if(dupeCountKey == 3) {

                if(dupeCounts.get(3) == 2) {
                    return(2500);
                } else if (dupeCountVals.get(3).get(0) == 1){
                    returnVal = 1000;
                } else {
                    returnVal = dupeCountVals.get(3).get(0) * 100;
                }

            } else if(dupeCountKey == 2) {
                if(dupeCounts.get(2) == 3) {
                    return(1500);
                }
            } else {
                if(dupeCounts.get(1) == 6) {
                    return(1500);
                }
            }

        }

        ArrayList<Integer> arl = dupeCountVals.get(1);
        if (arl != null){
            if (arl.contains(1)) {
                returnVal += 100;
            } 
            if (arl.contains(5)) {
                returnVal += 50;
            }
        }
        arl = dupeCountVals.get(2);
        if (arl != null){
            if (arl.contains(1)) {
                returnVal += 200;
            } 
            if (arl.contains(5)) {
                returnVal += 100;
            }
        }
        return(returnVal);
    }

    public int totalScore() {
        int score = 0;
        for (Integer s : scoreArr) {
            score += s;
        }
        return(score);
    }

    public void bankDice(ArrayList<Integer> arrList) {
        bankedDiceArr.add(arrList);
        int bankScore = score(arrList);
        System.out.println("Score of current choice is."+bankScore);
        scoreArr.add(bankScore);
    }

    /**
     * Method startTurn
     * Initailaizes the variables needed for a start of a turn.
     *
     * @return The return value is the score of the turn including all the banks.
     */
    public int startTurn() {
        bankedDiceArr = new ArrayList <ArrayList <Integer>>();
        scoreArr = new ArrayList <Integer>();
        diceArr.clear();
        for (int i=1;i<=6;i++) {
            diceArr.add(new Dice());
        }
        return(playRoll());
    }

    /**
     * Method playRoll
     *    Within a turn a player can have many rolls. This implements one roll and recursively calls the next roll unless the round finishes.
     * @return The return value is the score for that roll.
     */
    public int playRoll() {
        Integer val;
        boolean continueTurn;
        System.out.println("It's your turn "+ "!");
        ArrayList <Integer> numsArr = new ArrayList<Integer> ();
        ArrayList <Integer> valArr = new ArrayList <Integer>();
        continueTurn = false;
        numsArr = new ArrayList <Integer>();
        frame.setSize(1000, 1000);
        for (Dice die : diceArr) {
            final int key = die.getValueOfDice();

            valArr.add(key);

            if ((key == 1) || (key == 5)) {
                continueTurn = true;
            }
        }
        displayOutput="";
        FDisp.addDies(valArr);
        if (!continueTurn) {
            System.out.println("You farkled and gained 0 points"); 
            return(0);
        } else if(valArr.size() == 0) {
            System.out.println("You farkled and gained 0 points"); 
            return(0);
        } else {
            System.out.println(valArr);
            System.out.println("Select the dice you want to bank: ");
            //String str = in.nextLine();
            System.out.print("Waiting...");
            while (displayOutput == "")
            {
                //System.out.println("Sleeping"+displayOutput);
                try {Thread.sleep(1000);} catch(Exception e){}
            };
            System.out.println("Waiting complete");

            if (displayOutput.equals("keep")) {
                bankDice(valArr);
                return(totalScore());
            } else {
                String[] strArr = displayOutput.split(" ");
                for (String tStr : strArr) {
                    int num = Integer.parseInt(tStr);
                    numsArr.add(num);
                }
                // numsArr contains the positions of the dice that need to be kept. IN ascending order.
                Collections.sort(numsArr);
                ArrayList <Integer> bankedArr = new ArrayList <Integer>();
                int counter = 0;
                System.out.println("nums1:"+numsArr);
                for(Integer i : numsArr) {
                    bankedArr.add(valArr.remove(i - counter - 1));
                    diceArr.remove(i - counter - 1);
                    counter++;
                }
                bankDice(bankedArr);
                return playRoll();
            }
        }
    }
}

