package Atlassian;
/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k.
Each hour, she chooses some pile of bananas and eats k bananas from that pile.
If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Constraints:

1 <= piles.length <= 10^4
piles.length <= h <= 10^9
1 <= piles[i] <= 10^9
 */

import java.util.Arrays;

public class KokoEatingBanana {

    public int minEatingSpeed(int[] piles, int h) {

        /*
        * To eat one pile in an hour the eating speed should be equals or greater than the pile
        * And to finish all piles the return hour should be atleast equal or greater than the total no. (piles.length)
        * of piles. So to give min 1hr at each pile to finish a pile it would take piles.length hrs to finish all
        * piles. As already given piles.length <= h
        * So the minEatingRate is 1 banana/hr.
        * Now to the max pile koko's speed should be equal less or equal to it. Because going beyond the pile speed
        * doesn't expedite the finish. For eg if the max value is 30 and eating rate is 40/hr, so according to given condition
        * koko will not eat any more bananas during this hour. Math.ceil(maxPile/k) = 1.
        * So we can say maxEatingRate is maxPileValue
        * Now our ans lies in the range of [minEatingRate, maxEatingRate]
        * We have to find minimum int k, which means maxEatingRate could finish all the piles at time very less than h.
        * So we have to minimize it so that it is nearly equal to or less than h. If greater than h the piles won't be finished
        * before guard's return.
        * */


        int minEatingRate = 1;
        int maxEatingRate = Arrays.stream(piles).max().getAsInt();
        int ans = 0;

        while (minEatingRate <= maxEatingRate) {
            int mid = (maxEatingRate + minEatingRate) / 2;

            if (isRateOk(piles, mid, h)) {
                // Try to minimize the rate
                ans = mid;
                maxEatingRate = mid - 1;
            } else {
                // Try to maximize the rate
                minEatingRate = mid + 1;
            }
        }

        return ans;

    }

    private boolean isRateOk(int[] piles, int k, int h) {
        int totalTime = 0;
        for (int pile: piles) {
            totalTime += Math.ceil(pile/k);
        }
        return totalTime <= h;
    }
}
