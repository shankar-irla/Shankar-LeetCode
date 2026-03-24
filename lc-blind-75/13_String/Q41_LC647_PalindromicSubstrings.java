/*
-------------------------------------------------------
Problem ID   : LC647
Title        : Palindromic Substrings
Topic        : String
Pattern      : Expand Around Center / DP
Difficulty   : Medium

Problem Summary:
Given a string s, return the number of palindromic substrings.

Example:
Input  : "aaa"
Output : 6

Explanation:
"a", "a", "a", "aa", "aa", "aaa"

-------------------------------------------------------

Approach 1: Brute Force

- Generate all substrings
- Check palindrome

Time  : O(n³)
Space : O(1)

-------------------------------------------------------

Approach 2: Expand Around Center (Optimal)

- Expand for each index
- Count palindromes

Time  : O(n²)
Space : O(1)

-------------------------------------------------------

Approach 3: Dynamic Programming

- dp[i][j] = palindrome or not

Time  : O(n²)
Space : O(n²)

-------------------------------------------------------
*/

//import java.util.*;

public class Q41_LC647_PalindromicSubstrings {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int countBrute(String s){

        int count = 0;

        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){

                if(isPalindrome(s, i, j))
                    count++;
            }
        }

        return count;
    }

    private static boolean isPalindrome(String s, int l, int r){

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
    public static int countOptimal(String s){

        int count = 0;

        for(int i = 0; i < s.length(); i++){

            count += expand(s, i, i);     // odd
            count += expand(s, i, i+1);   // even
        }

        return count;
    }

    private static int expand(String s, int left, int right){

        int count = 0;

        while(left >= 0 && right < s.length() &&
              s.charAt(left) == s.charAt(right)){

            count++;
            left--;
            right++;
        }

        return count;
    }

    /*
    -------------------------------------------------------
    Approach 3: Dynamic Programming
    -------------------------------------------------------
    */
    public static int countDP(String s){

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;

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

                if(dp[i][j])
                    count++;
            }
        }

        return count;
    }

    // Optional testing
    public static void main(String[] args){

        String s = "aaa";

        System.out.println(countOptimal(s));

        /*
        // Brute
        System.out.println(countBrute(s));

        // DP
        System.out.println(countDP(s));
        */
    }
}


