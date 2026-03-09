/*
-------------------------------------------------------
Problem ID   : LC322
Title        : Coin Change
Topic        : Dynamic Programming
Pattern      : Unbounded Knapsack
Difficulty   : Medium

Problem Summary:
Given an array of coin denominations and a target amount,
return the minimum number of coins required to make that amount.

If the amount cannot be formed, return -1.

Example:
coins = [1,2,5], amount = 11

11 = 5 + 5 + 1 → Output: 3

-------------------------------------------------------

Approach 1: Recursion (Brute Force)
- Try all coins recursively
Time  : O(k^amount)
Space : O(amount)

Approach 2: Memoization (Top Down DP)
- Store results of subproblems
Time  : O(amount * coins)
Space : O(amount)

Approach 3: Tabulation (Bottom Up DP)
- Build dp array iteratively
Time  : O(amount * coins)
Space : O(amount)

-------------------------------------------------------
*/

import java.util.*;

public class Q13_LC322_CoinChange {

    /*
    -------------------------------------------------------
    Approach 1: Recursion (Brute Force)
    -------------------------------------------------------
    */
    public static int coinChangeRec(int[] coins, int amount) {

        if (amount == 0)
            return 0;

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {

            if (amount - coin >= 0) {

                int res = coinChangeRec(coins, amount - coin);

                if (res != -1)
                    minCoins = Math.min(minCoins, res + 1);
            }
        }

        return (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
    }

    /*
    -------------------------------------------------------
    Approach 2: Memoization
    -------------------------------------------------------
    */
    public static int coinChangeMemo(int[] coins, int amount, int[] dp) {

        if (amount == 0)
            return 0;

        if (dp[amount] != -1)
            return dp[amount];

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {

            if (amount - coin >= 0) {

                int res = coinChangeMemo(coins, amount - coin, dp);

                if (res != -1)
                    minCoins = Math.min(minCoins, res + 1);
            }
        }

        dp[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return dp[amount];
    }

    /*
    -------------------------------------------------------
    Approach 3: Tabulation (Optimal)
    -------------------------------------------------------
    */
    public static int coinChangeTab(int[] coins, int amount) {

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    // Optional testing
    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Recursion : " + coinChangeRec(coins, amount));

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        System.out.println("Memo      : " + coinChangeMemo(coins, amount, dp));
        System.out.println("Tab       : " + coinChangeTab(coins, amount));
    }
}