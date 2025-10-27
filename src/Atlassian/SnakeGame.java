package Atlassian;

import java.util.ArrayDeque;

public class SnakeGame {


    int height, width;
    ArrayDeque<int[]> q = new ArrayDeque<int[]>();
    int score;
    int i;
    int[][] food;

    public SnakeGame(int width, int height, int[][] food) {
        this.height = height;
        this.width = width;
        q.add(new int[]{0, 0});
        score = 0;
        i = 0;
        this.food = food;
    }

    public int move(String direction) {
        int[] head = q.getLast();
        int r = head[0], c = head[1];
        int nr = r, nc = c;
        switch (direction) {
            case "U":
                nr = r - 1;
                break;
            case "D":
                nr = r + 1;
                break;
            case "L":
                nc = c - 1;
                break;
            case "R":
                nc = c + 1;
                break;
        }

        if (nr < 0 || nr >= height || nc < 0 || nc >= width) {
            return -1;
        }

        if (i < food.length && nr == food[i][0] && nc == food[i][1]) {
            score++;
            i++;
        } else {
            q.removeFirst();
        }


        for (int[] occ : q) {
            if (occ[0] == nr && occ[1] == nc) {
                return -1;
            }
        }

        q.addLast(new int[]{nr, nc});
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

