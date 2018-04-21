/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */

import java.util.Arrays;
public class StampDispenser
{
    /**
     * Constructs a new StampDispenser that will be able to dispense the given 
     * types of stamps.
     *
     * @param stampDenominations The values of the types of stamps that the 
     *     machine should have.  Should be sorted in descending order and 
     *     contain at least a 1.
     */

    
    int[] stampDenominations;
    public StampDispenser(int[] stampDenominations)
    {
        this.stampDenominations = stampDenominations;
    }
 
    /**
     * Returns the minimum number of stamps that the machine can dispense to
     * fill the given request.
     *
     * @param request The total value of the stamps to be dispensed.
     */
    public int calcMinNumStampsToFillRequest(int request)
    {  
        int[] table = new int[request + 1];
        Arrays.fill(table, Integer.MIN_VALUE);
        table[0] = 0;
        System.out.println(Arrays.toString(table));
        return 0;
    }
    
    public static void main(String[] args)
    {
        int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
        StampDispenser stampDispenser = new StampDispenser(denominations);
        System.out.println(stampDispenser.calcMinNumStampsToFillRequest(18) == 3);
        // System.out.println(assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3);
    }
}
