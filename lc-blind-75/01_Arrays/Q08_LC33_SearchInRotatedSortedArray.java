/*
-------------------------------------------------------
Problem ID   : LC33
Title        : Search in Rotated Sorted Array
Topic        : Arrays / Binary Search
Pattern      : Binary Search on Rotated Array
Difficulty   : Medium

Problem Summary:
Given a rotated sorted array nums and a target value,
return the index of target if it exists, otherwise return -1.

Example:
nums = [4,5,6,7,0,1,2], target = 0 → Output: 4

-------------------------------------------------------

Approach 1: Brute Force
- Traverse array and compare with target.
Time  : O(n)
Space : O(1)

Approach 2: Find Pivot + Binary Search
- Find rotation index (smallest element).
- Decide which half to binary search.
Time  : O(log n)
Space : O(1)

Approach 3: Optimal Binary Search
- At every step, one half is sorted.
- Determine which side target belongs to.

Time  : O(log n)
Space : O(1)

-------------------------------------------------------
*/

//import java.util.*;

public class Q08_LC33_SearchInRotatedSortedArray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int searchBrute(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }

        return -1;
    }

    /*
    -------------------------------------------------------
    Approach 2: Find Pivot + Binary Search
    -------------------------------------------------------
    */
    public static int searchPivot(int[] nums, int target) {

        int n = nums.length;

        // find pivot
        int left = 0, right = n - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }

        int pivot = left;

        // binary search in correct half
        left = 0;
        right = n - 1;

        if (target >= nums[pivot] && target <= nums[right])
            left = pivot;
        else
            right = pivot - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    /*
    -------------------------------------------------------
    Approach 3: Optimal Rotated Binary Search
    -------------------------------------------------------
    */
    public static int searchOptimal(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // left side sorted
            if (nums[left] <= nums[mid]) {

                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            // right side sorted
            else {

                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        System.out.println("Brute   : " + searchBrute(nums, target));
        System.out.println("Pivot   : " + searchPivot(nums, target));
        System.out.println("Optimal : " + searchOptimal(nums, target));
    }
}