/*
-------------------------------------------------------
Problem ID   : LC704
Title        : Binary Search
Topic        : Binary Search
Pattern      : Divide & Conquer
Difficulty   : Easy

Problem Summary:
Given a sorted array of integers nums and a target value,
return the index of target if found, else return -1.

Example:
Input  : nums = [-1,0,3,5,9,12], target = 9
Output : 4

-------------------------------------------------------

Approach 1: Iterative (Optimal)

- Use left & right pointers
- Find mid
- Compare and shrink search space

Time  : O(log n)
Space : O(1)

-------------------------------------------------------

Approach 2: Recursive

- Same logic using recursion

Time  : O(log n)
Space : O(log n)

-------------------------------------------------------
*/

public class Q48_LC704_BinarySearch {

    /*
    -------------------------------------------------------
    Approach 1: Iterative
    -------------------------------------------------------
    */
    public static int searchIterative(int[] nums, int target){

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){

            int mid = left + (right - left) / 2;

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return -1;
    }

    /*
    -------------------------------------------------------
    Approach 2: Recursive
    -------------------------------------------------------
    */
    public static int searchRecursive(int[] nums, int target){
        return helper(nums, target, 0, nums.length - 1);
    }

    private static int helper(int[] nums, int target, int left, int right){

        if(left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if(nums[mid] == target)
            return mid;

        if(nums[mid] < target)
            return helper(nums, target, mid + 1, right);
        else
            return helper(nums, target, left, mid - 1);
    }

    // Optional testing
    public static void main(String[] args){

        int[] nums = {-1,0,3,5,9,12};
        int target = 9;

        System.out.println(searchIterative(nums, target));

        /*
        // Recursive version
        System.out.println(searchRecursive(nums, target));
        */
    }
}