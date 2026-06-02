/*
-------------------------------------------------------
Problem ID   : LC74
Title        : Search a 2D Matrix
Topic        : Binary Search
Pattern      : Binary Search on Matrix
Difficulty   : Medium

Problem Summary:
Given an m x n matrix where:

- Each row is sorted
- First element of row > last element of previous row

Return true if target exists.

Example:

Input:
[
 [1,3,5,7],
 [10,11,16,20],
 [23,30,34,60]
]

target = 3

Output:
true

-------------------------------------------------------

Approach 1: Row Scan + Binary Search

- Find possible row
- Binary search inside row

Time  : O(m + log n)
Space : O(1)

-------------------------------------------------------

Approach 2: Single Binary Search (Optimal)

Idea:
Treat matrix as a virtual sorted array

Index mapping:

row = mid / cols
col = mid % cols

Time  : O(log(m*n))
Space : O(1)

-------------------------------------------------------
*/

public class Q51_LC74_SearchA2DMatrix {

    /*
    -------------------------------------------------------
    Approach 1: Row Scan + Binary Search
    -------------------------------------------------------
    */
    public static boolean searchMatrixRowWise(int[][] matrix, int target){

        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i++){

            if(target >= matrix[i][0] &&
               target <= matrix[i][cols - 1]){

                int left = 0;
                int right = cols - 1;

                while(left <= right){

                    int mid = left + (right - left) / 2;

                    if(matrix[i][mid] == target)
                        return true;

                    if(matrix[i][mid] < target)
                        left = mid + 1;
                    else
                        right = mid - 1;
                }
            }
        }

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 2: Binary Search on Matrix
    -------------------------------------------------------
    */
    public static boolean searchMatrix(int[][] matrix, int target){

        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while(left <= right){

            int mid = left + (right - left) / 2;

            int row = mid / cols;
            int col = mid % cols;

            int value = matrix[row][col];

            if(value == target)
                return true;

            if(value < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }

    // Optional testing
    public static void main(String[] args){

        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };

        int target = 3;

        System.out.println(
                searchMatrix(matrix, target)
        );

        /*
        // Row-wise version
        System.out.println(
                searchMatrixRowWise(matrix, target)
        );
        */
    }
}