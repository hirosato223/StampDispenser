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
        if (stampDenominations.length < 1) {
            throw new IllegalArgumentException("Denominations must contain at least one value.");
        }
        if (stampDenominations[stampDenominations.length - 1] != 1) {
            throw new IllegalArgumentException("Denominations should contain a 1, at the end of the list.");
        }

        int[] seen = new int[stampDenominations.length];

        for (int i = 0; i < stampDenominations.length; i++) {
            int currentDenom = stampDenominations[i];
            if (currentDenom <= 0) { 
                throw new IllegalArgumentException("Denominations must be positive values.");
            } else if (i > 0 && currentDenom == seen[i - 1]) {
                throw new IllegalArgumentException("Denominations must be unique.");
            } else if (i > 0 && currentDenom > seen[i - 1]) { 
                throw new IllegalArgumentException("Denominations must be in descending order.");                
            }
            seen[i] = currentDenom;
        }
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
        if (request == 0) return 0;
        if (request == 1) return 1;

        int[] table = new int[request + 1];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[0] = 0;
        for (int i = 1; i <= request; i++) {
            for (int j = 0; j < this.stampDenominations.length; j++) {
                int currentCoinVal = this.stampDenominations[j];
                if (currentCoinVal <= i) {
                    table[i] = Math.min(table[i], 1 + table[i - currentCoinVal]);
                }
            }
        }
        return table[request];
    }
    
    public static void main(String[] args)
    {
        // Normal case 1
        int[] denominations1 = { 90, 30, 24, 10, 6, 2, 1 };
        StampDispenser stampDispenser1 = new StampDispenser(denominations1);
        assert stampDispenser1.calcMinNumStampsToFillRequest(18) == 3;

        // Normal case 2
        int[] denominations2 = {9, 6, 5, 1};
        StampDispenser stampDispenser2 = new StampDispenser(denominations2);
        assert stampDispenser2.calcMinNumStampsToFillRequest(11) == 2;

        // Edge case; expected value: 0
        int[] denominations3 = {45, 10, 5, 3, 1};
        StampDispenser stampDispenser3 = new StampDispenser(denominations3);
        assert stampDispenser3.calcMinNumStampsToFillRequest(0) == 0;

        // Only stamp value: 1
        int[] denominations4 = {1};
        StampDispenser stampDispenser4 = new StampDispenser(denominations4);
        assert stampDispenser4.calcMinNumStampsToFillRequest(40) == 40;
    }
}
