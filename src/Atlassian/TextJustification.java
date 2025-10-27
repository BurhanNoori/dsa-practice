package Atlassian;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        //The logic is to take the words from an array then append an space after it and store it
        //keep doing this untill the stored value meets or cross the threshold.
        //Once we reach the threshold add the value in the list of string and repeat for the remaining values.

        //Corner case add space before each word excluding the first word and last of each line i!=0.
        //Add space to the last word only (i==words.length())
        List<String> ans = new ArrayList<>();
        justify(words, maxWidth, ans, 0);
        return ans;
    }

    private void justify(String[] words, int maxWidth, List<String> ans, int i) {
        if (words.length == i) {
            return;
        }
        StringBuilder str = new StringBuilder();
        while (str.length() + words[i].length() + 1 <= maxWidth) {
            if (i !=0 ) {
                str.append(" ");
            }
            str.append(words[i]);
            i++;
        }
        int addSpaces = maxWidth - str.length();
        fineTuneSpace(str, addSpaces);
        ans.add(str.toString());
        justify(words, maxWidth, ans, i);
    }

    private void fineTuneSpace(StringBuilder str, int addSpaces) {
        if (str.indexOf(" ") == -1) {
            str.append(" ".repeat(addSpaces));
            return;
        }
        while (addSpaces != 0) {
            int i = str.indexOf(" ", 0);
            str.insert(i, " ");
            addSpaces--;
            i++;
        }
    }
}
