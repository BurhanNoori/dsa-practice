package Atlassian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.compare;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 * */
public class MergeIntervals {
    /**
     * You are given 2D array and you have to merge the overlapping intervals
     * First you need to sort the array based on the 0th index value
     * So that you can merge them.
     * Now compare the 1st index value of each row and 0th index value of the next row
     * If intervals[row][1] > intervals[row+1][0], increase the range last limit from [row][1] to [row+1][1]
     * But here is an edge case, it may be possible that your intervals[row][1] is greater than the [row+1][1]
     * So take Math.max(intervals[row][0], intervals[row+1][0]). Don't put the values in the answer array
     * untill you get the intervals[row][1] < intervals[row][0]. At this point put you values in the answer array.
     *
     * */

    public int[][] mergeIntervals (int[][] intervals) {
        if (intervals.length < 2) return intervals;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int w = intervals[0][0];
        int x = intervals[0][1];
        int y = 0;
        int z = 0;
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            y = intervals[i][0];
            z = intervals[i][1];
            if ( x >= y) {
                x = Math.max(x, intervals[i][1]);
            } else {
                ans.add(new int[]{w, x});
                w = y;
                x = z;
            }
        }

        //Now consider at i = lastIndex
        ans.add(new int[]{w, x});

        return ans.toArray(new int[ans.size()][]);
    }

}
