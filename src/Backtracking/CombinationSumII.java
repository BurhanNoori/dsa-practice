package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSumII (int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        /* Sorting is needed to avoid duplicates */
        Arrays.sort(nums);
        dfs(result, new ArrayList<>(), nums, target, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, ArrayList<Integer> comb, int[] nums, int target, int i) {

        //Before, this "if" statement, was written down so
        //there was an edge case where the nums.length == i
        //was reached along with target == 0, so that case got missed.

        if (target == 0) {
            result.add(new ArrayList<>(comb));
            return;
        }

        if (target < 0 || nums.length == i) {
            return;
        }

        //1. Add the current idx number and find next
        comb.add(nums[i]);
        dfs(result, comb, nums, target - nums[i], i+1);
        comb.removeLast();

        //2. Now skip the current element also skip its duplicates
        while (i+1 < nums.length && nums[i] == nums[i+1]) {
            i++;
        }
        dfs(result, comb, nums, target, i+1);
    }
}
