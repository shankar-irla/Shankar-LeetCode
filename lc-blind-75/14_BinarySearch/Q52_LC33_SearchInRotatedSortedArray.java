/*
-------------------------------------------------------
Problem ID   : LC33
Title        : Search in Rotated Sorted Array
Topic        : Binary Search
Pattern      : Modified Binary Search
Difficulty   : Medium

Problem Summary:
Given a rotated sorted array nums and a target,
return its index if found, otherwise -1.

Example:
Input:
nums = [4,5,6,7,0,1,2]
target = 0

Output:
4

-------------------------------------------------------

Approach 1: Brute Force

- Linear search

Time  : O(n)
Space : O(1)

-------------------------------------------------------

Approach 2: Modified Binary Search (Optimal)

Idea:
- One half is always sorted
- Determine which half is sorted
- Check if target belongs there

Time  : O(log n)
Space : O(1)

-------------------------------------------------------
*/

public class Q52_LC33_SearchInRotatedSortedArray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int searchBrute(int[] nums, int target){

        for(int i = 0; i < nums.length; i++){

            if(nums[i] == target)
                return i;
        }

        return -1;
    }

    /*
    -------------------------------------------------------
    Approach 2: Modified Binary Search
    -------------------------------------------------------
    */
    public static int searchOptimal(int[] nums, int target){

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){

            int mid = left + (right - left) / 2;

            if(nums[mid] == target)
                return mid;

            // Left half sorted
            if(nums[left] <= nums[mid]){

                if(target >= nums[left] &&
                   target < nums[mid]){

                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
            // Right half sorted
            else{

                if(target > nums[mid] &&
                   target <= nums[right]){

                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    // Optional testing
    public static void main(String[] args){

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        System.out.println(
                searchOptimal(nums, target)
        );

        /*
        // Brute Force
        System.out.println(
                searchBrute(nums, target)
        );
        */
    }
}