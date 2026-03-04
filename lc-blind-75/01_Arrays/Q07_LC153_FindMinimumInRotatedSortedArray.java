/*
-------------------------------------------------------
Problem ID   : LC153
Title        : Find Minimum in Rotated Sorted Array
Topic        : Arrays / Binary Search
Pattern      : Binary Search on Rotated Array
Difficulty   : Medium

Problem Summary:
Suppose an array sorted in ascending order is rotated
between 1 and n times. Return the minimum element.

Example:
nums = [4,5,6,7,0,1,2] → Output: 0

-------------------------------------------------------

Approach 1: Brute Force
- Traverse the entire array and track minimum.
Time  : O(n)
Space : O(1)

Approach 2: Linear Break Detection
- Find where nums[i] > nums[i+1].
- The next element is the minimum.
Time  : O(n)
Space : O(1)

Approach 3: Binary Search (Optimal)
- If mid element > right → min lies right side
- Else → min lies left side including mid

Time  : O(log n)
Space : O(1)

-------------------------------------------------------
*/

//import java.util.*;

public class Q07_LC153_FindMinimumInRotatedSortedArray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int findMinBrute(int[] nums) {

        int min = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
        }

        return min;
    }

    /*
    -------------------------------------------------------
    Approach 2: Detect Rotation Point
    -------------------------------------------------------
    */
    public static int findMinBreak(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }

        return nums[0];
    }

    /*
    -------------------------------------------------------
    Approach 3: Binary Search (Optimal)
    -------------------------------------------------------
    */
    public static int findMinOptimal(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            else {
                right = mid;
            }
        }

        return nums[left];
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};

        System.out.println("Brute   : " + findMinBrute(nums));
        System.out.println("Break   : " + findMinBreak(nums));
        System.out.println("Optimal : " + findMinOptimal(nums));
    }
}