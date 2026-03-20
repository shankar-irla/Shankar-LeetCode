/*
-------------------------------------------------------
Problem ID   : LC54
Title        : Spiral Matrix
Topic        : Matrix
Pattern      : Boundary Traversal
Difficulty   : Medium

Problem Summary:
Given an m x n matrix, return all elements
in spiral order.

Example:
Input:
[
 [1,2,3],
 [4,5,6],
 [7,8,9]
]

Output:
[1,2,3,6,9,8,7,4,5]

-------------------------------------------------------

Approach: Boundary Traversal

Idea:
- Maintain 4 boundaries:
  top, bottom, left, right
- Traverse in 4 directions:
  left → right
  top → bottom
  right → left
  bottom → top

Shrink boundaries after each step

Time  : O(m*n)
Space : O(1) (excluding output list)

-------------------------------------------------------
*/

import java.util.*;

public class Q35_LC54_SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix){

        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right){

            // left → right
            for(int j = left; j <= right; j++)
                result.add(matrix[top][j]);
            top++;

            // top → bottom
            for(int i = top; i <= bottom; i++)
                result.add(matrix[i][right]);
            right--;

            // right → left
            if(top <= bottom){
                for(int j = right; j >= left; j--)
                    result.add(matrix[bottom][j]);
                bottom--;
            }

            // bottom → top
            if(left <= right){
                for(int i = bottom; i >= top; i--)
                    result.add(matrix[i][left]);
                left++;
            }
        }

        return result;
    }

    // Optional testing
    public static void main(String[] args){

        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        List<Integer> result = spiralOrder(matrix);

        System.out.println(result);
    }
}