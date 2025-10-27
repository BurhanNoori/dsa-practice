package Atlassian;
/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
 * and an integer n, return true if n new flowers can be planted in the flowerbed without violating
 * the no-adjacent-flowers rule and false otherwise.
 *
 * */
public class FlowerBed {
    public boolean canPlaceFlower (int[] flowerbed, int n) {
        /*
        * We do not want any flower beds one place ahead of our current index and one place behind our index.
        * So check for that while traversing the array.
        * We only replace 0 values so putting all the rules on indexes having values 0.
        * Also our n should always be greater than 0
        * Handle corner cases like first, last and only element.
        * */

        for ( int i = 0; i < flowerbed.length; i++ ) {
            if (flowerbed[i] == 0 && n > 0) {
                //Case 1. First element
                if (i==0) {
                    if (i+1 == flowerbed.length) {
                        flowerbed[i] = 1;
                        n--;
                    } else if (flowerbed[i+1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                }


                //Case 2. Last element
                else if (i+1 == flowerbed.length && flowerbed[i-1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                }

                //Case 3. other elements
                else {
                    if (flowerbed[i-1] == 0 && flowerbed[i+1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                }
            }

            if (n == 0) return true;
        }

        return n == 0 ? true : false;
    }
}
