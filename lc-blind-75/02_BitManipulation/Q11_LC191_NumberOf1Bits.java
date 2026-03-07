/*
-------------------------------------------------------
Problem ID   : LC191
Title        : Number of 1 Bits
Topic        : Binary / Bit Manipulation
Pattern      : Bit Counting
Difficulty   : Easy

Problem Summary:
Given an unsigned integer n, return the number of '1' bits
in its binary representation (also known as the Hamming weight).

Example:
Input  : n = 11
Binary : 1011
Output : 3

-------------------------------------------------------

Approach 1: Brute Force (Convert to Binary String)
- Convert number to binary string
- Count '1's

Time  : O(32)
Space : O(32)

Approach 2: Bit Checking (Right Shift)
- Check last bit using (n & 1)
- Shift number right

Time  : O(32)
Space : O(1)

Approach 3: Brian Kernighan's Algorithm (Optimal)
- Remove the lowest set bit each iteration
- n = n & (n - 1)

Time  : O(number of set bits)
Space : O(1)

-------------------------------------------------------
*/

public class Q11_LC191_NumberOf1Bits {

    /*
    -------------------------------------------------------
    Approach 1: Convert to Binary String
    -------------------------------------------------------
    */
    public static int hammingWeightBrute(int n) {

        String binary = Integer.toBinaryString(n);
        int count = 0;

        for (char c : binary.toCharArray()) {
            if (c == '1')
                count++;
        }

        return count;
    }

    /*
    -------------------------------------------------------
    Approach 2: Bit Check with Right Shift
    -------------------------------------------------------
    */
    public static int hammingWeightShift(int n) {

        int count = 0;

        while (n != 0) {

            count += (n & 1); // check last bit
            n >>>= 1;         // unsigned right shift
        }

        return count;
    }

    /*
    -------------------------------------------------------
    Approach 3: Brian Kernighan's Algorithm (Optimal)
    -------------------------------------------------------
    */
    public static int hammingWeightOptimal(int n) {

        int count = 0;

        while (n != 0) {

            n = n & (n - 1); // remove lowest set bit
            count++;
        }

        return count;
    }

    // Optional testing
    public static void main(String[] args) {

        int n = 11;

        System.out.println("Brute   : " + hammingWeightBrute(n));
        System.out.println("Shift   : " + hammingWeightShift(n));
        System.out.println("Optimal : " + hammingWeightOptimal(n));
    }
}