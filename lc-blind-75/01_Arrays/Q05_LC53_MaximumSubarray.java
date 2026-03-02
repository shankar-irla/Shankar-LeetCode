/*
-------------------------------------------------------
Problem ID   : LC53
Title        : Maximum Subarray
Topic        : Arrays
Pattern      : Kadane's Algorithm
Difficulty   : Medium

Problem Summary:
Given an integer array nums, find the contiguous subarray 
with the largest sum and return its sum.

-------------------------------------------------------

Approach 1: Brute Force
- Generate all subarrays.
- Track maximum sum.
Time  : O(n²)
Space : O(1)

Approach 2: Prefix Sum
- Use prefix array to compute subarray sums.
Time  : O(n²)
Space : O(n)

Approach 3: Kadane's Algorithm (Optimal)
- At each index:
  currentSum = max(nums[i], currentSum + nums[i])
- Track global maximum.
Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

//import java.util.*;

public class Q05_LC53_MaximumSubarray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int maxSubArrayBrute(int[] nums) {

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;

            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    /*
    -------------------------------------------------------
    Approach 2: Prefix Sum
    -------------------------------------------------------
    */
    public static int maxSubArrayPrefix(int[] nums) {

        int n = nums.length;
        int[] prefix = new int[n];

        prefix[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int maxSum = nums[0];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                int currentSum = prefix[j] - (i > 0 ? prefix[i - 1] : 0);
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    /*
    -------------------------------------------------------
    Approach 3: Kadane's Algorithm (Optimal)
    -------------------------------------------------------
    */
    public static int maxSubArrayOptimal(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println("Brute   : " + maxSubArrayBrute(nums));
        System.out.println("Prefix  : " + maxSubArrayPrefix(nums));
        System.out.println("Optimal : " + maxSubArrayOptimal(nums));
    }
}