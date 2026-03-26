/*
-------------------------------------------------------
Problem ID   : LC76
Title        : Minimum Window Substring
Topic        : Sliding Window
Pattern      : Variable Sliding Window + Frequency Map
Difficulty   : Hard

Problem Summary:
Given strings s and t, return the smallest substring
of s that contains all characters of t (including duplicates).

If no such substring exists, return "".

Example:
Input  : s = "ADOBECODEBANC", t = "ABC"
Output : "BANC"

-------------------------------------------------------

Approach: Sliding Window (Optimal)

Idea:
- Expand window using right pointer
- Track required characters using map
- When valid → shrink window from left
- Maintain minimum window

Time  : O(n)
Space : O(1) / O(128)

-------------------------------------------------------
*/

//import java.util.*;

public class Q44_LC76_MinimumWindowSubstring {

    public static String minWindow(String s, String t){

        if(s.length() < t.length())
            return "";

        int[] freq = new int[128];

        for(char c : t.toCharArray()){
            freq[c]++;
        }

        int left = 0;
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for(int right = 0; right < s.length(); right++){

            if(freq[s.charAt(right)] > 0){
                count--;
            }

            freq[s.charAt(right)]--;

            // valid window
            while(count == 0){

                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    start = left;
                }

                freq[s.charAt(left)]++;

                if(freq[s.charAt(left)] > 0){
                    count++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ?
                "" : s.substring(start, start + minLen);
    }

    // Optional testing
    public static void main(String[] args){

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s, t));
    }
}