/*
-------------------------------------------------------
Problem ID   : LC567
Title        : Permutation in String
Topic        : Sliding Window
Pattern      : Fixed Window + Frequency Match
Difficulty   : Medium

Problem Summary:
Given two strings s1 and s2, return true if s2 contains
a permutation of s1.

Example:
Input  : s1 = "ab", s2 = "eidbaooo"
Output : true

Explanation:
"ba" is a permutation of "ab"

-------------------------------------------------------

Approach 1: Brute Force

- Generate all substrings of length s1
- Compare with permutations

Time  : O(n * k log k)
Space : O(1)

-------------------------------------------------------

Approach 2: Sliding Window (Optimal)

Idea:
- Maintain frequency array for s1
- Slide window of size s1.length()
- Match frequency arrays

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

//import java.util.*;

public class Q46_LC567_PermutationInString {

    public static boolean checkInclusion(String s1, String s2){

        if(s1.length() > s2.length())
            return false;

        int[] freq = new int[26];

        // build freq for s1
        for(char c : s1.toCharArray()){
            freq[c - 'a']++;
        }

        int left = 0;

        for(int right = 0; right < s2.length(); right++){

            // include current char
            freq[s2.charAt(right) - 'a']--;

            // window size exceeded
            if(right - left + 1 > s1.length()){
                freq[s2.charAt(left) - 'a']++;
                left++;
            }

            // check match
            if(isZero(freq)){
                return true;
            }
        }

        return false;
    }

    private static boolean isZero(int[] freq){

        for(int val : freq){
            if(val != 0)
                return false;
        }

        return true;
    }

    // Optional testing
    public static void main(String[] args){

        String s1 = "ab";
        String s2 = "eidbaooo";

        System.out.println(checkInclusion(s1, s2));
    }
}