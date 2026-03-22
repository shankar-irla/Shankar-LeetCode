/*
-------------------------------------------------------
Problem ID   : LC5
Title        : Longest Palindromic Substring
Topic        : String
Pattern      : Expand Around Center / DP
Difficulty   : Medium

Problem Summary:
Given a string s, return the longest palindromic substring.

Example:
Input  : "babad"
Output : "bab" (or "aba")

-------------------------------------------------------

Approach 1: Brute Force

- Generate all substrings
- Check palindrome

Time  : O(n³)
Space : O(1)

-------------------------------------------------------

Approach 2: Expand Around Center (Optimal)

Idea:
- Every character can be center
- Expand for odd & even length

Time  : O(n²)
Space : O(1)

-------------------------------------------------------

Approach 3: Dynamic Programming

- dp[i][j] = true if substring i..j is palindrome

Time  : O(n²)
Space : O(n²)

-------------------------------------------------------
*/

import java.util.*;

public class Q40_LC5_LongestPalindromicSubstring {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static String longestPalindromeBrute(String s){

        String res = "";

        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){

                String sub = s.substring(i, j+1);

                if(isPalindrome(sub) && sub.length() > res.length()){
                    res = sub;
                }
            }
        }

        return res;
    }

    private static boolean isPalindrome(String s){

        int l = 0, r = s.length()-1;

        while(l < r){
            if(s.charAt(l++) != s.charAt(r--))
                return false;
        }

        return true;
    }

    /*
    -------------------------------------------------------
    Approach 2: Expand Around Center (Optimal)
    -------------------------------------------------------
    */
    public static String longestPalindromeOptimal(String s){

        if(s == null || s.length() < 1)
            return "";

        int start = 0, end = 0;

        for(int i = 0; i < s.length(); i++){

            int len1 = expand(s, i, i);     // odd
            int len2 = expand(s, i, i+1);   // even

            int len = Math.max(len1, len2);

            if(len > end - start){

                start = i - (len - 1) / 2;
                end   = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expand(String s, int left, int right){

        while(left >= 0 && right < s.length() &&
              s.charAt(left) == s.charAt(right)){

            left--;
            right++;
        }

        return right - left - 1;
    }

    /*
    -------------------------------------------------------
    Approach 3: Dynamic Programming
    -------------------------------------------------------
    */
    public static String longestPalindromeDP(String s){

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        String res = "";

        for(int len = 1; len <= n; len++){

            for(int i = 0; i + len - 1 < n; i++){

                int j = i + len - 1;

                if(len == 1){
                    dp[i][j] = true;
                }
                else if(len == 2){
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }
                else{
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
                }

                if(dp[i][j] && len > res.length()){
                    res = s.substring(i, j+1);
                }
            }
        }

        return res;
    }

    // Optional testing
    public static void main(String[] args){

        String s = "babad";

        System.out.println(longestPalindromeOptimal(s));

        /*
        // Brute
        System.out.println(longestPalindromeBrute(s));

        // DP
        System.out.println(longestPalindromeDP(s));
        */
    }
}

