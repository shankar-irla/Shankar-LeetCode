/*
-------------------------------------------------------
Problem ID   : LC11
Title        : Container With Most Water
Topic        : Arrays
Pattern      : Two Pointers
Difficulty   : Medium

Problem Summary:
Given n non-negative integers representing heights of vertical lines,
find two lines that together with the x-axis form a container that holds
the maximum amount of water.

Water Area Formula:
area = min(height[left], height[right]) * (right - left)

-------------------------------------------------------

Approach 1: Brute Force
- Try every pair of lines.
- Compute area and track maximum.

Time  : O(n²)
Space : O(1)

Approach 2: Optimal Two Pointers
- Start pointers at both ends.
- Move the pointer with smaller height.
- Because area depends on the smaller height.

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q10_LC11_ContainerWithMostWater {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int maxAreaBrute(int[] height) {

        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {

            for (int j = i + 1; j < height.length; j++) {

                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    /*
    -------------------------------------------------------
    Approach 2: Optimal Two Pointers
    -------------------------------------------------------
    */
    public static int maxAreaOptimal(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {

            int h = Math.min(height[left], height[right]);
            int width = right - left;

            maxArea = Math.max(maxArea, h * width);

            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }

    // Optional testing
    public static void main(String[] args) {

        int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println("Brute   : " + maxAreaBrute(height));
        System.out.println("Optimal : " + maxAreaOptimal(height));
    }
}