/*
-------------------------------------------------------
Problem ID   : LC48
Title        : Rotate Image
Topic        : Matrix
Pattern      : Transpose + Reverse
Difficulty   : Medium

Problem Summary:
You are given an n x n matrix.
Rotate the matrix by 90 degrees clockwise in-place.

Example:

Input:
[
 [1,2,3],
 [4,5,6],
 [7,8,9]
]

Output:
[
 [7,4,1],
 [8,5,2],
 [9,6,3]
]

-------------------------------------------------------

Approach 1: Brute (Extra Matrix)

- Create a new matrix
- Map values accordingly

Time  : O(n²)
Space : O(n²)

-------------------------------------------------------

Approach 2: Optimal (In-place)

Step 1: Transpose matrix
Step 2: Reverse each row

Time  : O(n²)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q36_LC48_RotateImage {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int[][] rotateBrute(int[][] matrix){

        int n = matrix.length;
        int[][] res = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                res[j][n - 1 - i] = matrix[i][j];
            }
        }

        return res;
    }

    /*
    -------------------------------------------------------
    Approach 2: Optimal (Transpose + Reverse)
    -------------------------------------------------------
    */
    public static void rotateOptimal(int[][] matrix){

        int n = matrix.length;

        // Step 1: Transpose
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for(int i = 0; i < n; i++){

            int left = 0;
            int right = n - 1;

            while(left < right){

                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
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
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println("Original:");
        printMatrix(matrix);

        rotateOptimal(matrix);

        System.out.println("\nRotated:");
        printMatrix(matrix);

        /*
        // Brute test
        int[][] res = rotateBrute(matrix);
        printMatrix(res);
        */
    }
}