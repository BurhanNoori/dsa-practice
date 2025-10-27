package Backtracking;

/*
* Given a 2-D grid of characters board and a string word,
* return true if the word is present in the grid, otherwise return false.
* For the word to be present it must be possible to form it with a path in the board
* with horizontally or vertically neighboring cells.
* The same cell may not be used more than once in a word.
* */

import java.util.HashSet;

public class WordSearch {

    int [][] DIRECTIONS = {
            {-1,  0}, //up
            { 1,  0}, //down
            { 0, -1}, //left
            { 0,  1}, //right
    };

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (wordSearch(row, col, board, word, new HashSet<>())) return true;
            }
        }

        return false;
    }

    private boolean wordSearch(int row, int col, char[][] board, String word, HashSet<String> visited) {

        /* 1. Base condition: No char to left to search
          This line is hit when no char left to search in the board
          */
        if (word.isEmpty()) return true;

        // 2. The condition to reject the search
        int rows = board.length;
        int cols = board[0].length;
        String pos = row + "," + col;
        if (row < 0 ||
                row >= rows ||
                col < 0 || col >= cols ||
                board[row][col] != word.charAt(0) ||
                visited.contains(pos)
        )
            return false;

        // 3. If pass the above conditions means the char is found in the grid so add the position in visited
        visited.add(pos);

        //4. Now try to find the next char in next 4 possible directions;

        for (int[] dir: DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            //This loop checks all four directions if the below function returns false means try next position

            //If we didn't get in all four directions we will return false and remove the last add value
            //because it would not lead us to the word we are looking in the board and it should not be
            //considered as visited in upcoming iterations

            if (wordSearch(newRow, newCol, board, word.substring(1), visited)) {
                //The line. no 39 would produce a domino effect and above call once get hit by line no. 39 all the
                // other calls in the stack will reach to line no. 71 and start returning true.
                return true;
            }
        }
        //5. Coming out the loop means we didn't find the char in the all four possible directions.
        visited.remove(pos);

        // 6. No path from the here so, try with the next starting point in the grid.
        return false;
    }

    /*
     Key point: Removal should be done after exploring the char in all the four directions which
     means after the end of for loop.
     If the function call didn't get false it means it has got the char and now we can proceed with the next word.

     The key concept in this problem is that we have to keep calling the function if we are not hitting the line
     return false. If we get return false then check the other possible directions
     */

    /**
     * The time and space complexity of the solution is as follows:
     *
     */

}
