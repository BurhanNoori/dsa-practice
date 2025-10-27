package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetII {

    public List<List<Integer>> subsetII(int[] nums) {
       List<List<Integer>> result = new ArrayList<>();
       Arrays.sort(nums);
       dfs(result, new ArrayList<Integer>(), nums, 0);
       return result;
    }

    private void dfs(List<List<Integer>> result, ArrayList<Integer> subsetsWithoutDuplicates, int[] nums, int i) {
        if (i == nums.length) {
           result.add(new ArrayList<>(subsetsWithoutDuplicates));
           return;
        }

        subsetsWithoutDuplicates.add(nums[i]);
        dfs(result, subsetsWithoutDuplicates, nums, i + 1);
        subsetsWithoutDuplicates.removeLast();

        while (i + 1 < nums.length && nums[i] == nums[i+1]) {
            i++;
        }
        dfs(result, subsetsWithoutDuplicates, nums, i + 1);
    }


}
