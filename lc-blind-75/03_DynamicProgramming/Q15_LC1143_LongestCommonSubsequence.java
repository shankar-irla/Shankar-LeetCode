/*
-------------------------------------------------------
Problem ID   : LC1143
Title        : Longest Common Subsequence
Topic        : Dynamic Programming
Pattern      : 2D DP
Difficulty   : Medium

Problem Summary:
Given two strings text1 and text2,
return the length of their longest common subsequence.

A subsequence is a sequence derived by deleting
some or no characters without changing order.

Example:
text1 = "abcde"
text2 = "ace"

LCS = "ace"
Output = 3

-------------------------------------------------------

Approach 1: Recursion (Brute)
- Compare characters from end
Time  : O(2^(m+n))
Space : O(m+n)

Approach 2: Memoization (Top Down DP)
- Store results of subproblems

Time  : O(m*n)
Space : O(m*n)

Approach 3: Tabulation (Bottom Up DP)
- Build 2D DP table

Time  : O(m*n)
Space : O(m*n)

Approach 4: Space Optimized
- Use two rows instead of full matrix

Time  : O(m*n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q15_LC1143_LongestCommonSubsequence {

    /*
    -------------------------------------------------------
    Approach 1: Recursion
    -------------------------------------------------------
    */
    public static int lcsRec(String s1, String s2, int i, int j) {

        if (i == 0 || j == 0)
            return 0;

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            return 1 + lcsRec(s1, s2, i - 1, j - 1);

        return Math.max(
                lcsRec(s1, s2, i - 1, j),
                lcsRec(s1, s2, i, j - 1)
        );
    }

    /*
    -------------------------------------------------------
    Approach 2: Memoization
    -------------------------------------------------------
    */
    public static int lcsMemo(String s1, String s2, int i, int j, int[][] dp) {

        if (i == 0 || j == 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            dp[i][j] = 1 + lcsMemo(s1, s2, i - 1, j - 1, dp);
        else
            dp[i][j] = Math.max(
                    lcsMemo(s1, s2, i - 1, j, dp),
                    lcsMemo(s1, s2, i, j - 1, dp)
            );

        return dp[i][j];
    }

    /*
    -------------------------------------------------------
    Approach 3: Tabulation
    -------------------------------------------------------
    */
    public static int lcsTab(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    /*
    -------------------------------------------------------
    Approach 4: Space Optimized
    -------------------------------------------------------
    */
    public static int lcsOptimal(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];

                else
                    curr[j] = Math.max(prev[j], curr[j - 1]);
            }

            prev = curr.clone();
        }

        return prev[n];
    }

    // Optional testing
    public static void main(String[] args) {

        String s1 = "abcde";
        String s2 = "ace";

        System.out.println("Recursion : " + lcsRec(s1, s2, s1.length(), s2.length()));

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        System.out.println("Memo      : " + lcsMemo(s1, s2, s1.length(), s2.length(), dp));
        System.out.println("Tab       : " + lcsTab(s1, s2));
        System.out.println("Optimal   : " + lcsOptimal(s1, s2));
    }
}