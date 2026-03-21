/*
-------------------------------------------------------
Problem ID   : LC79
Title        : Word Search
Topic        : Matrix / Backtracking
Pattern      : DFS + Backtracking
Difficulty   : Medium

Problem Summary:
Given a 2D board and a word,
return true if the word exists in the grid.

Rules:
- Letters must be adjacent (up, down, left, right)
- Same cell cannot be reused

Example:

Board:
A B C E
S F C S
A D E E

Word = "ABCCED" → true

-------------------------------------------------------

Approach: DFS + Backtracking

Idea:
- Start DFS from each cell
- Match characters one by one
- Mark visited cells
- Backtrack (undo visit)

Time  : O(m*n * 4^L)
Space : O(L) recursion stack

-------------------------------------------------------
*/

public class Q37_LC79_WordSearch {

    public static boolean exist(char[][] board, String word){

        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(dfs(board, word, i, j, 0))
                    return true;
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word,
                               int i, int j, int index){

        // matched entire word
        if(index == word.length())
            return true;

        // boundary + mismatch check
        if(i < 0 || j < 0 ||
           i >= board.length || j >= board[0].length ||
           board[i][j] != word.charAt(index))
            return false;

        char temp = board[i][j];
        board[i][j] = '#'; // mark visited

        // explore 4 directions
        boolean found =
                dfs(board, word, i+1, j, index+1) ||
                dfs(board, word, i-1, j, index+1) ||
                dfs(board, word, i, j+1, index+1) ||
                dfs(board, word, i, j-1, index+1);

        board[i][j] = temp; // backtrack

        return found;
    }

    // Optional testing
    public static void main(String[] args){

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String word = "ABCCED";

        System.out.println(exist(board, word));
    }
}