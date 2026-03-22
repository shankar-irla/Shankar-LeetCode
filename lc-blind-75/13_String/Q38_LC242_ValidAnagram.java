/*
-------------------------------------------------------
Problem ID   : LC242
Title        : Valid Anagram
Topic        : String
Pattern      : Frequency Count / Hashing
Difficulty   : Easy

Problem Summary:
Given two strings s and t, return true if t is an anagram of s.

An anagram means:
Both strings contain same characters with same frequency.

Example:
s = "anagram"
t = "nagaram"

Output: true

-------------------------------------------------------

Approach 1: Sorting

- Sort both strings
- Compare them

Time  : O(n log n)
Space : O(n)

-------------------------------------------------------

Approach 2: Frequency Array (Optimal)

- Count characters using array[26]
- Increment for s, decrement for t

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q38_LC242_ValidAnagram {

    /*
    -------------------------------------------------------
    Approach 1: Sorting
    -------------------------------------------------------
    */
    public static boolean isAnagramSort(String s, String t){

        if(s.length() != t.length())
            return false;

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    /*
    -------------------------------------------------------
    Approach 2: Frequency Array (Optimal)
    -------------------------------------------------------
    */
    public static boolean isAnagramOptimal(String s, String t){

        if(s.length() != t.length())
            return false;

        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for(int count : freq){
            if(count != 0)
                return false;
        }

        return true;
    }

    // Optional testing
    public static void main(String[] args){

        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagramOptimal(s, t));

        /*
        // Sorting approach test
        System.out.println(isAnagramSort(s, t));
        */
    }
}


