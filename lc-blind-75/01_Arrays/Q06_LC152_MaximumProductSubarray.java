/*
-------------------------------------------------------
Problem ID   : LC152
Title        : Maximum Product Subarray
Topic        : Arrays
Pattern      : Dynamic Tracking (Max & Min)
Difficulty   : Medium

Problem Summary:
Given an integer array nums, find the contiguous subarray 
that has the largest product and return the product.

-------------------------------------------------------

Approach 1: Brute Force
- Generate all subarrays.
- Compute product for each.
Time  : O(n²)
Space : O(1)

Approach 2: Prefix & Suffix Traversal
- Traverse from left and right.
- Reset product at zero.
Time  : O(n)
Space : O(1)

Approach 3: Optimal (Track Max & Min)
- Maintain:
    currentMax
    currentMin
- Because negative × negative = positive
Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

//import java.util.*;

public class Q06_LC152_MaximumProductSubarray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int maxProductBrute(int[] nums) {

        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int product = 1;

            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return maxProduct;
    }

    /*
    -------------------------------------------------------
    Approach 2: Prefix & Suffix Scan
    -------------------------------------------------------
    */
    public static int maxProductPrefixSuffix(int[] nums) {

        int maxProduct = Integer.MIN_VALUE;
        int prefix = 1;
        int suffix = 1;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            prefix = (prefix == 0 ? 1 : prefix) * nums[i];
            suffix = (suffix == 0 ? 1 : suffix) * nums[n - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(prefix, suffix));
        }

        return maxProduct;
    }

    /*
    -------------------------------------------------------
    Approach 3: Optimal (Max & Min Tracking)
    -------------------------------------------------------
    */
    public static int maxProductOptimal(int[] nums) {

        int currentMax = nums[0];
        int currentMin = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int num = nums[i];

            if (num < 0) {
                // swap because sign flips
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            currentMax = Math.max(num, currentMax * num);
            currentMin = Math.min(num, currentMin * num);

            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {2, 3, -2, 4};

        System.out.println("Brute        : " + maxProductBrute(nums));
        System.out.println("Prefix/Suffix: " + maxProductPrefixSuffix(nums));
        System.out.println("Optimal      : " + maxProductOptimal(nums));
    }
}