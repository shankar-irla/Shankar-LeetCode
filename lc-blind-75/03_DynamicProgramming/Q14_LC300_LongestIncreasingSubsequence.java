/*
-------------------------------------------------------
Problem ID   : LC300
Title        : Longest Increasing Subsequence
Topic        : Dynamic Programming
Pattern      : LIS / Binary Search Optimization
Difficulty   : Medium

Problem Summary:
Given an integer array nums, return the length of the
longest strictly increasing subsequence.

Example:
nums = [10,9,2,5,3,7,101,18]

LIS = [2,3,7,101]
Output = 4

-------------------------------------------------------

Approach 1: Recursion (Brute)
- Try include / exclude decisions
Time  : O(2^n)
Space : O(n)

Approach 2: DP (Tabulation)
- dp[i] = LIS ending at index i
Time  : O(n²)
Space : O(n)

Approach 3: Binary Search (Optimal)
- Maintain a list of smallest tail values
Time  : O(n log n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q14_LC300_LongestIncreasingSubsequence {

    /*
    -------------------------------------------------------
    Approach 1: Recursion (Brute)
    -------------------------------------------------------
    */
    public static int lisRec(int[] nums, int index, int prevIndex) {

        if (index == nums.length)
            return 0;

        int notTake = lisRec(nums, index + 1, prevIndex);

        int take = 0;

        if (prevIndex == -1 || nums[index] > nums[prevIndex])
            take = 1 + lisRec(nums, index + 1, index);

        return Math.max(take, notTake);
    }

    /*
    -------------------------------------------------------
    Approach 2: DP (O(n²))
    -------------------------------------------------------
    */
    public static int lisDP(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int maxLIS = 1;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {

                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return maxLIS;
    }

    /*
    -------------------------------------------------------
    Approach 3: Binary Search (Optimal O(n log n))
    -------------------------------------------------------
    */
    public static int lisOptimal(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for (int num : nums) {

            int pos = Collections.binarySearch(list, num);

            if (pos < 0)
                pos = -(pos + 1);

            if (pos == list.size())
                list.add(num);
            else
                list.set(pos, num);
        }

        return list.size();
    }

    // Optional testing
    public static void main(String[] args) {

        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println("Recursion : " + lisRec(nums, 0, -1));
        System.out.println("DP        : " + lisDP(nums));
        System.out.println("Optimal   : " + lisOptimal(nums));
    }
}