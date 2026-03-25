/*
---------------------------------------------------
Problem ID   : LC125
Title        : Valid Palindrome
Topic        : String
Pattern      : Two Pointer
Difficulty   : Easy

Problem Summary:
Given a string s, determine if it is a palindrome,
considering only alphanumeric characters and ignoring cases.

Example:
Input  : "A man, a plan, a canal: Panama"
Output : true

---------------------------------------------------

Approach 1: Clean String + Reverse

- Remove non-alphanumeric
- Convert to lowercase
- Compare with reverse

Time  : O(n)
Space : O(n)

-------------------------------------------------------

Approach 2: Two Pointer (Optimal)

- Use left & right pointers
- Skip non-alphanumeric characters
- Compare lowercase characters

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

public class Q42_LC125_ValidPalindrome {

    /*
    -------------------------------------------------------
    Approach 1: Clean + Reverse
    -------------------------------------------------------
    */
    public static boolean isPalindromeClean(String s){

        StringBuilder cleaned = new StringBuilder();

        for(char c : s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                cleaned.append(Character.toLowerCase(c));
            }
        }

        String str = cleaned.toString();
        String rev = cleaned.reverse().toString();

        return str.equals(rev);
    }

    /*
    -------------------------------------------------------
    Approach 2: Two Pointer (Optimal)
    -------------------------------------------------------
    */
    public static boolean isPalindromeOptimal(String s){

        int left = 0;
        int right = s.length() - 1;

        while(left < right){

            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }

            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }

            if(Character.toLowerCase(s.charAt(left)) !=
               Character.toLowerCase(s.charAt(right))){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Optional testing
    public static void main(String[] args){

        String s = "A man, a plan, a canal: Panama";

        System.out.println(isPalindromeOptimal(s));

        /*
        // Clean version
        System.out.println(isPalindromeClean(s));
        */
    }
}