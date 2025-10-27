package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum (int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), nums, target, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> comb, int[] nums, int target, int i) {
        if (target < 0 || i==nums.length) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<Integer>(comb));
            return;
        }

        comb.add(nums[i]);
        dfs(result, comb, nums, target - nums[i], i);
        comb.removeLast();
        dfs(result, comb, nums, target, i + 1);
    }
}
