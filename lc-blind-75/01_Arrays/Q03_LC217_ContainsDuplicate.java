/*
-------------------------------------------------------
Problem ID   : LC217
Title        : Contains Duplicate
Topic        : Arrays
Pattern      : Hashing / Sorting
Difficulty   : Easy

Problem Summary:
Given an integer array nums, return true if any value appears 
at least twice in the array. Otherwise, return false.

Approach 1: Brute Force
- Compare every pair of elements.
- If any two elements are equal → duplicate found.

Time Complexity  : O(n²)
Space Complexity : O(1)

Approach 2: Sorting
- Sort the array.
- Check adjacent elements for equality.

Time Complexity  : O(n log n)
Space Complexity : O(1) (in-place sort)

Approach 3: HashSet (Optimal)
- Traverse array once.
- Store elements in a HashSet.
- If element already exists → duplicate found.

Time Complexity  : O(n)
Space Complexity : O(n)
-------------------------------------------------------
*/

import java.util.*;

public class Q03_LC217_ContainsDuplicate {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static boolean containsDuplicateBrute(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 2: Sorting
    -------------------------------------------------------
    */
    public static boolean containsDuplicateSorting(int[] nums) {

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 3: HashSet (Optimal)
    -------------------------------------------------------
    */
    public static boolean containsDuplicateOptimal(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.add(num)) {   // add() returns false if already present
                return true;
            }
        }

        return false;
    }

    // Optional local testing
    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 3, 4};

        System.out.println("Brute Force : " + containsDuplicateBrute(nums1.clone()));
        System.out.println("Sorting     : " + containsDuplicateSorting(nums1.clone()));
        System.out.println("Optimal     : " + containsDuplicateOptimal(nums1));

        System.out.println("Brute Force : " + containsDuplicateBrute(nums2.clone()));
        System.out.println("Sorting     : " + containsDuplicateSorting(nums2.clone()));
        System.out.println("Optimal     : " + containsDuplicateOptimal(nums2));
    }
}