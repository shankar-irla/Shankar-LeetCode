/*
-------------------------------------------------------
Problem ID   : LC3
Title        : Longest Substring Without Repeating Characters
Topic        : Sliding Window
Pattern      : Variable Sliding Window + HashMap
Difficulty   : Medium

Problem Summary:
Given a string s, find the length of the longest substring
without repeating characters.

Example:
Input  : "abcabcbb"
Output : 3

-------------------------------------------------------

Approach 1: Sliding Window + HashSet

- Expand right pointer
- If duplicate → shrink from left

Time  : O(n)
Space : O(n)

-------------------------------------------------------

Approach 2: Sliding Window + HashMap (Optimal)

- Store last index of character
- Jump left pointer directly

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/


import java.util.*;



public class Q47_LC3_LongestSubstringSlidingWindow {

    public static int lengthOfLongestSubstringSet(String s){

        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for(int right = 0; right < s.length(); right++){

            while(set.contains(s.charAt(right))){
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static int lengthOfLongestSubstringMap(String s){

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for(int right = 0; right < s.length(); right++){

            char ch = s.charAt(right);

            if(map.containsKey(ch)){
                left = Math.max(left, map.get(ch) + 1);
            }

            map.put(ch, right);

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}