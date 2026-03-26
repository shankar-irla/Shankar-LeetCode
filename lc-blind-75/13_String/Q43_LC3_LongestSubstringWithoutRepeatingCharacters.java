/*
-------------------------------------------------------
Problem ID   : LC3
Title        : Longest Substring Without Repeating Characters
Topic        : String
Pattern      : Sliding Window
Difficulty   : Medium

Problem Summary:
Given a string s, find the length of the longest substring
without repeating characters.

Example:
Input  : "abcabcbb"
Output : 3

Explanation:
"abc" is the longest substring.

-------------------------------------------------------

Approach 1: Sliding Window + HashSet

- Expand window
- If duplicate → shrink from left

Time  : O(n)
Space : O(n)

-------------------------------------------------------

Approach 2: Sliding Window + HashMap (Optimal)

- Store last index of characters
- Jump left pointer directly

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q43_LC3_LongestSubstringWithoutRepeatingCharacters {

    /*
    -------------------------------------------------------
    Approach 1: HashSet
    -------------------------------------------------------
    */
    public static int lengthOfLongestSubstringSet(String s){

        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

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

    /*
    -------------------------------------------------------
    Approach 2: HashMap (Optimal)
    -------------------------------------------------------
    */
    public static int lengthOfLongestSubstringMap(String s){

        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int maxLen = 0;

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

    // Optional testing
    public static void main(String[] args){

        String s = "abcabcbb";

        System.out.println(lengthOfLongestSubstringMap(s));

        /*
        // Set version
        System.out.println(lengthOfLongestSubstringSet(s));
        */
    }
}