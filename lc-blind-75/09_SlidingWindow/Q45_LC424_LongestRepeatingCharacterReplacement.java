/*
-------------------------------------------------------
Problem ID   : LC424
Title        : Longest Repeating Character Replacement
Topic        : Sliding Window
Pattern      : Sliding Window + Frequency Count
Difficulty   : Medium

Problem Summary:
Given a string s and an integer k,
you can replace at most k characters.

Return the length of the longest substring
containing same character after replacements.

Example:
Input  : s = "ABAB", k = 2
Output : 4

-------------------------------------------------------

Approach 1: Brute Force

- Try all substrings
- Check if <= k replacements needed

Time  : O(n³)
Space : O(1)

-------------------------------------------------------

Approach 2: Sliding Window (Optimal)

Idea:
- Maintain window [left, right]
- Track most frequent character count
- If window size - maxFreq > k → shrink window

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

public class Q45_LC424_LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k){

        int[] freq = new int[26];

        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;

        for(int right = 0; right < s.length(); right++){

            freq[s.charAt(right) - 'A']++;

            maxFreq = Math.max(maxFreq,
                    freq[s.charAt(right) - 'A']);

            // if invalid window
            if((right - left + 1) - maxFreq > k){

                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Optional testing
    public static void main(String[] args){

        String s = "AABABBA";
        int k = 1;

        System.out.println(characterReplacement(s, k));
    }
}