/*
-------------------------------------------------------
Problem ID   : LC121
Title        : Best Time to Buy and Sell Stock
Topic        : Arrays
Pattern      : Running Minimum / Kadane Variant
Difficulty   : Easy

Problem Summary:
Given an array prices where prices[i] is the price of a stock on day i,
find the maximum profit achievable by buying on one day and selling on a later day.
If no profit is possible, return 0.

Approach 1 (Brute Force):
- Try every pair (buy, sell).
- Track maximum profit.

Approach 2 (Running Minimum - Optimal):
- Track minimum price seen so far.
- At each day, compute profit = currentPrice - minPrice.
- Update max profit.

Approach 3 (Kadane Variant Thinking):
- Convert to daily differences.
- Find maximum subarray sum.

Time Complexity :
- Brute Force  : O(n²)
- Optimal      : O(n)
- Kadane Style : O(n)

Space Complexity: O(1)
-------------------------------------------------------
*/

import java.util.*;

public class Q02_LC121_BestTimeToBuyAndSellStock {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int maxProfitBruteForce(int[] prices) {

        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    /*
    -------------------------------------------------------
    Approach 2: Running Minimum (Optimal)
    -------------------------------------------------------
    */
    public static int maxProfitOptimal(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {

            minPrice = Math.min(minPrice, price);
            int profit = price - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    /*
    -------------------------------------------------------
    Approach 3: Kadane Variant (Difference Array Thinking)
    -------------------------------------------------------
    */
    public static int maxProfitKadane(int[] prices) {

        int maxProfit = 0;
        int currentProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            int diff = prices[i] - prices[i - 1];
            currentProfit = Math.max(0, currentProfit + diff);
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }

    // Optional local testing
    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println("Brute Force     : " + maxProfitBruteForce(prices));
        System.out.println("Optimal         : " + maxProfitOptimal(prices));
        System.out.println("Kadane Variant  : " + maxProfitKadane(prices));
    }
}