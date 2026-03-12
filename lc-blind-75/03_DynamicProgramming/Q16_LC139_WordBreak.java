/*
-------------------------------------------------------
Problem ID   : LC139
Title        : Word Break
Topic        : Dynamic Programming
Pattern      : String DP / HashSet
Difficulty   : Medium

Problem Summary:
Given a string s and a dictionary of words wordDict,
return true if s can be segmented into a sequence of
one or more dictionary words.

Example:
s = "leetcode"
wordDict = ["leet", "code"]

Output = true
Because: "leet" + "code"

-------------------------------------------------------

Approach 1: Recursion (Brute Force)
- Try breaking string at every index
Time  : O(2^n)
Space : O(n)

Approach 2: Memoization
- Store results of already solved substrings

Time  : O(n²)
Space : O(n)

Approach 3: DP Tabulation (Optimal)
- dp[i] = can substring(0,i) be segmented

Time  : O(n²)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q16_LC139_WordBreak {

    /*
    -------------------------------------------------------
    Approach 1: Recursion
    -------------------------------------------------------
    */
    public static boolean wordBreakRec(String s, Set<String> dict, int start) {

        if (start == s.length())
            return true;

        for (int end = start + 1; end <= s.length(); end++) {

            if (dict.contains(s.substring(start, end)) &&
                    wordBreakRec(s, dict, end)) {

                return true;
            }
        }

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 2: Memoization
    -------------------------------------------------------
    */
    public static boolean wordBreakMemo(String s, Set<String> dict, int start, Boolean[] dp) {

        if (start == s.length())
            return true;

        if (dp[start] != null)
            return dp[start];

        for (int end = start + 1; end <= s.length(); end++) {

            if (dict.contains(s.substring(start, end)) &&
                    wordBreakMemo(s, dict, end, dp)) {

                return dp[start] = true;
            }
        }

        return dp[start] = false;
    }

    /*
    -------------------------------------------------------
    Approach 3: DP Tabulation (Optimal)
    -------------------------------------------------------
    */
    public static boolean wordBreakOptimal(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] && dict.contains(s.substring(j, i))) {

                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // Optional testing
    public static void main(String[] args) {

        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        Set<String> dict = new HashSet<>(wordDict);

        System.out.println("Recursion : " + wordBreakRec(s, dict, 0));

        Boolean[] dp = new Boolean[s.length()];
        System.out.println("Memo      : " + wordBreakMemo(s, dict, 0, dp));

        System.out.println("Optimal   : " + wordBreakOptimal(s, wordDict));
    }
}