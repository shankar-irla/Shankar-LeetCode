/*
-------------------------------------------------------
Problem ID   : LC73
Title        : Set Matrix Zeroes
Topic        : Matrix
Pattern      : In-place Marking
Difficulty   : Medium

Problem Summary:
Given an m x n matrix, if an element is 0,
set its entire row and column to 0.

Do it in-place.

Example:
Input:
[
 [1,1,1],
 [1,0,1],
 [1,1,1]
]

Output:
[
 [1,0,1],
 [0,0,0],
 [1,0,1]
]

-------------------------------------------------------

Approach 1: Brute (Extra Space)

- Use row[] and col[] arrays
- Mark rows and columns containing 0

Time  : O(m*n)
Space : O(m + n)

-------------------------------------------------------

Approach 2: Optimal (In-place)

- Use first row and first column as markers
- Track separately if first row/column should be zero

Time  : O(m*n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q34_LC73_SetMatrixZeroes {

    /*
    -------------------------------------------------------
    Approach 1: Extra Space
    -------------------------------------------------------
    */
    public static void setZeroesBrute(int[][] matrix){

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(rows[i] || cols[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
    -------------------------------------------------------
    Approach 2: In-place (Optimal)
    -------------------------------------------------------
    */
    public static void setZeroesOptimal(int[][] matrix){

        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check first row
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 0)
                firstRowZero = true;
        }

        // Check first column
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0)
                firstColZero = true;
        }

        // Use first row & col as markers
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){

                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Fill matrix based on markers
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){

                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // Handle first row
        if(firstRowZero){
            for(int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }

        // Handle first column
        if(firstColZero){
            for(int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    // Utility: Print matrix
    public static void printMatrix(int[][] matrix){

        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }

    // Optional testing
    public static void main(String[] args){

    int[][] matrix = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
    };

    System.out.println("Original Matrix:");
    printMatrix(matrix);

    // Use Optimal Approach (Default)
    setZeroesOptimal(matrix);

    System.out.println("\nAfter Applying Optimal Approach:");
    printMatrix(matrix);

    /*
    // If you want to test brute:
    int[][] matrix2 = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
    };

    setZeroesBrute(matrix2);

    System.out.println("\nBrute Force Output:");
    printMatrix(matrix2);
    */
}
}