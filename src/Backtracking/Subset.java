package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> subset, int[] nums, int idx) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[idx]);
        dfs(result, subset, nums, idx + 1);
        subset.removeLast();
        dfs(result, subset, nums, idx + 1);

    }
}
