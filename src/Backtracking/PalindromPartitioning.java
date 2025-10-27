package Backtracking;

import java.util.ArrayList;
import java.util.List;


                    /*
 call backtrack(0, [])
 ├─ end=1: substr="a"  (palindrome)
 │   └─ call backtrack(1, ["a"])
 │       ├─ end=2: substr="a"
 │       │   └─ call backtrack(2, ["a","a"])
 │       │       ├─ end=3: substr="b"
 │       │       │   └─ call backtrack(3, ["a","a","b"])
 │       │       │       → start==s.length(), record ["a","a","b"]
 │       │       └─ backtrack step: remove "b" → path back to ["a","a"]
 │       └─ backtrack step: remove "a" → path back to ["a"]
 │
 ├─ end=2: substr="aa"
 │   └─ call backtrack(2, ["aa"])
 │       ├─ end=3: substr="b"
 │       │   └─ call backtrack(3, ["aa","b"])
 │       │       → record ["aa","b"]
 │       └─ backtrack: remove "b" → path back to ["aa"]
 │   └─ backtrack: remove "aa" → path back to []
 │
 └─ end=3: substr="aab" (not palindrome → skip)

                    * */

public class PalindromPartitioning {

    public List<List<String>> palindromPartition (String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, result, new ArrayList<String>(), 0);
        return result;
    }

    private void backtrack(String s, List<List<String>> result, ArrayList<String> path, int start) {

        if (start >= s.length()) {
            path.add(s);
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String subStr = s.substring(start, end);
            if(isPalindrom(subStr)) {
                path.add(subStr);
                backtrack(s,result, path, end);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrom(String s) {
        if (s.length() <= 1) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
