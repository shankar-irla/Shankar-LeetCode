/*
-------------------------------------------------------
Problem ID   : LC200
Title        : Number of Islands
Topic        : Graph
Pattern      : Grid DFS / BFS
Difficulty   : Medium

Problem Summary:
Given an m x n grid of '1's (land) and '0's (water),
return the number of islands.

An island is surrounded by water and formed by
connecting adjacent lands horizontally or vertically.

Example:
grid =
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1

Output = 3

-------------------------------------------------------

Approach 1: DFS (Flood Fill)
- When we see land, start DFS
- Convert connected land → water

Time  : O(m*n)
Space : O(m*n)

Approach 2: BFS
- Use queue to explore neighbors

Time  : O(m*n)
Space : O(m*n)

-------------------------------------------------------
*/

import java.util.*;

public class Q18_LC200_NumberOfIslands {

    /*
    -------------------------------------------------------
    DFS Approach
    -------------------------------------------------------
    */

    public static int numIslandsDFS(char[][] grid) {

        int count = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {

                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {

        if (i < 0 || j < 0 ||
            i >= grid.length || j >= grid[0].length ||
            grid[i][j] == '0')
            return;

        grid[i][j] = '0';

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    /*
    -------------------------------------------------------
    BFS Approach
    -------------------------------------------------------
    */

    public static int numIslandsBFS(char[][] grid) {

        int count = 0;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {

                    count++;

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});

                    grid[i][j] = '0';

                    while (!q.isEmpty()) {

                        int[] curr = q.poll();

                        for (int[] d : dirs) {

                            int x = curr[0] + d[0];
                            int y = curr[1] + d[1];

                            if (x >= 0 && y >= 0 &&
                                x < grid.length &&
                                y < grid[0].length &&
                                grid[x][y] == '1') {

                                grid[x][y] = '0';
                                q.add(new int[]{x,y});
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    // Optional testing
    public static void main(String[] args) {

        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        System.out.println("DFS : " + numIslandsDFS(grid));
    }
}