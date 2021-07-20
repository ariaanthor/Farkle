
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // score, name, and score if they pass 10,000
    private int score = 0;
    private final String name;
    private int finalScore;

    /**
     * Constructor for objects of class Player
     */
    public Player(String tempName)
    {
        // initialize instance variables
        score = 0;
        name = tempName;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    //adds to their score at the end of turn (doesnt add anything if they  farkle)
    public void incrementScore(int num) {
        score += num;
        System.out.println(name + "'s new score is: " + score);
    }

    public boolean declareWinner() {
        if (score >= 10000) {
            finalScore = score;
            return true;
        }
        return false;
    }
}
