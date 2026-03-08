/*
-------------------------------------------------------
Problem ID   : LC70
Title        : Climbing Stairs
Topic        : Dynamic Programming
Pattern      : Fibonacci DP
Difficulty   : Easy

Problem Summary:
You are climbing a staircase with n steps.

Each time you can climb:
1 step OR 2 steps.

Return the number of distinct ways to reach the top.

Example:
n = 3

Ways:
1+1+1
1+2
2+1

Output: 3

-------------------------------------------------------

Approach 1: Recursion (Brute Force)
- Try both options (1 step / 2 step)
Time  : O(2^n)
Space : O(n) recursion stack

Approach 2: Memoization (Top Down DP)
- Store already computed results

Time  : O(n)
Space : O(n)

Approach 3: Tabulation (Bottom Up DP)
- Build DP array iteratively

Time  : O(n)
Space : O(n)

Approach 4: Optimal Fibonacci
- Only track last two values

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q12_LC70_ClimbingStairs {

    /*
    -------------------------------------------------------
    Approach 1: Recursion (Brute)
    -------------------------------------------------------
    */
    public static int climbStairsRec(int n) {

        if (n <= 2)
            return n;

        return climbStairsRec(n - 1) + climbStairsRec(n - 2);
    }

    /*
    -------------------------------------------------------
    Approach 2: Memoization
    -------------------------------------------------------
    */
    public static int climbStairsMemo(int n, int[] dp) {

        if (n <= 2)
            return n;

        if (dp[n] != -1)
            return dp[n];

        dp[n] = climbStairsMemo(n - 1, dp) +
                climbStairsMemo(n - 2, dp);

        return dp[n];
    }

    /*
    -------------------------------------------------------
    Approach 3: Tabulation
    -------------------------------------------------------
    */
    public static int climbStairsTab(int n) {

        if (n <= 2)
            return n;

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /*
    -------------------------------------------------------
    Approach 4: Optimal Fibonacci
    -------------------------------------------------------
    */
    public static int climbStairsOptimal(int n) {

        if (n <= 2)
            return n;

        int prev1 = 2;
        int prev2 = 1;

        for (int i = 3; i <= n; i++) {

            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    // Optional testing
    public static void main(String[] args) {

        int n = 5;

        System.out.println("Recursion : " + climbStairsRec(n));

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println("Memo      : " + climbStairsMemo(n, dp));
        System.out.println("Tab       : " + climbStairsTab(n));
        System.out.println("Optimal   : " + climbStairsOptimal(n));
    }
}