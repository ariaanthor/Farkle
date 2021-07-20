import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

//this class has a bunch of test examples that tests all of the niche examples of potential outputs

public class TestScore {
    private static Farkle game ;

    public static void setUp()
    {
        game = new Farkle();
    }

    public static ArrayList<Integer> fromString(String arrstr)
    {
        ArrayList<Integer> alist = new ArrayList<Integer>();
        String[] strArr = arrstr.split(",");

        for (String i : strArr)
        {
            alist.add(Integer.valueOf(i));
        }
        return alist;
    }

    public static void testScore(String arr, int score)
    {
        game = new Farkle();
        int newscore = game.score(fromString(arr));
        if (newscore == score) {
            System.out.println("Passed:[" + arr + "]:" + score);
        }
        else {
            System.out.println("Failed:[" + arr + "]:" + newscore + " doesn't match expected result:"+ score);
        }
    }
    public static void testOne()
    {
        testScore("1",100);
        testScore("2",0);
        testScore("3",0);
        testScore("4",0);
        testScore("5",50);
        testScore("6",0);
    }

    public static void testTwo()
    {
        testScore("1,1",200);
        testScore("1,2",100);
        testScore("1,5",150);
        testScore("5,1",150);
        testScore("2,2",0);
        testScore("6,5",50);
    }
    public static void testThree() {
        testScore("1,1,5", 250);
        testScore("2,5,3", 50);
        testScore("5,5,5", 500);
        testScore("1,1,1", 1000);
    }

    public static void testFour() {
        testScore("1,1,1,5", 1050);
        testScore("2,3,4,5", 50);
        testScore("1,1,1,1", 1000);
    }

    public static void testFive() {
        testScore("1,1,1,1,1", 2000);
        testScore("5,5,5,5,5", 2000);
        testScore("2,2,2,2,2", 2000);
        testScore("1,1,1,1,5", 1050);
        testScore("2,2,2,2,5", 1050);
        testScore("2,2,2,2,3", 1000);
    }

    public static void testSix() {
        testScore("1,1,1,1,1,1", 3000);
        testScore("1,2,3,4,5,6", 1500);
        testScore("2,2,2,2,2,2", 3000);
        testScore("2,2,2,2,1,1", 1500);
        testScore("2,2,2,2,4,4", 1500);
        testScore("2,2,2,3,3,3", 2500);
        testScore("1,1,1,5,5,5", 2500);
        testScore("1,1,2,2,5,5", 1500);
        testScore("1,1,2,2,5,5", 1500);
    }

    public static void main(String[] args) {
        setUp();
        testOne();
        testTwo();
        testThree();
        testFour();
        testFive();
        testSix();
    }

}
