/*
-------------------------------------------------------
Problem ID   : LC238
Title        : Product of Array Except Self
Topic        : Arrays
Pattern      : Prefix + Suffix
Difficulty   : Medium

Problem Summary:
Given an integer array nums, return an array answer such that
answer[i] is equal to the product of all elements of nums except nums[i].

You must solve it without using division and in O(n) time.

-------------------------------------------------------

Approach 1: Brute Force
- For each index, multiply all other elements.
Time  : O(n²)
Space : O(1)

Approach 2: Prefix & Suffix Arrays
- Compute prefix products.
- Compute suffix products.
- Multiply prefix[i] * suffix[i].
Time  : O(n)
Space : O(n)

Approach 3: Optimal (O(1) Extra Space)
- Use output array for prefix.
- Maintain running suffix product.
Time  : O(n)
Space : O(1) (excluding output array)

-------------------------------------------------------
*/

import java.util.*;

public class Q04_LC238_ProductOfArrayExceptSelf {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int[] productExceptSelfBrute(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }

            result[i] = product;
        }

        return result;
    }

    /*
    -------------------------------------------------------
    Approach 2: Prefix & Suffix Arrays
    -------------------------------------------------------
    */
    public static int[] productExceptSelfBetter(int[] nums) {

        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] result = new int[n];

        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    /*
    -------------------------------------------------------
    Approach 3: Optimal (O(1) Extra Space)
    -------------------------------------------------------
    */
    public static int[] productExceptSelfOptimal(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Store prefix products directly in result
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with suffix product on the fly
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        System.out.println("Brute   : " + Arrays.toString(productExceptSelfBrute(nums)));
        System.out.println("Better  : " + Arrays.toString(productExceptSelfBetter(nums)));
        System.out.println("Optimal : " + Arrays.toString(productExceptSelfOptimal(nums)));
    }
}