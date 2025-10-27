package Backtracking;

/*
                              []
                               |
                              [1]
                      /                 \
                  [2,1]                [1,2]
               /    |    \          /    |    \
       [3,2,1][2,3,1][2,1,3]    [3,1,2][1,3,2][1,2,3]

*/



import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public List<List<Integer>> permutation (int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> permutation, int[] nums, int i) {

        if (i == nums.length) {
            result.add(permutation);
            return;
        }

        int curr = nums[i];
        int size = permutation.size();

        // Insert curr at all possible positions in permutation
        for (int idx = i; idx <= size; idx++) {
            List<Integer> list = new ArrayList<>(permutation);
            list.add(idx, curr);
            dfs(result, list, nums, i+1);
        }

    }
}
