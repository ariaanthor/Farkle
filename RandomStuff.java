import java.util.*;
/**
 * Write a description of class RandomStuff here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomStuff
{

    public static void printEvenElements(ArrayList<Integer> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) % 2 == 0)
            {
                System.out.print(list.get(i) + ", ");
            }
        }
    }

    public static void main(String[] args)
    {
        //instantiate ArrayList and fill with Integers
        ArrayList<Integer> values = new ArrayList<Integer>();
        int[] nums = {1, 44, 7, 9, -16, 3, 2};
        for (int i = 0; i < nums.length; i ++)
        {
            values.add(nums[i]);
        }
        printEvenElements(values);
        //("Expected Result: 44, -16, 2,");
    }
}
