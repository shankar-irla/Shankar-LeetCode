/*
-------------------------------------------------------
Problem ID   : LC153
Title        : Find Minimum in Rotated Sorted Array
Topic        : Binary Search
Pattern      : Modified Binary Search
Difficulty   : Medium

Problem Summary:
Suppose an array sorted in ascending order is rotated
between 1 and n times.

Return the minimum element.

Example:

Input  : [3,4,5,1,2]
Output : 1

-------------------------------------------------------

Approach 1: Brute Force

- Traverse entire array
- Keep minimum

Time  : O(n)
Space : O(1)

-------------------------------------------------------

Approach 2: Modified Binary Search (Optimal)

Idea:
- Compare mid with right
- Minimum always lies in unsorted half

Time  : O(log n)
Space : O(1)

-------------------------------------------------------
*/

public class Q53_LC153_FindMinimumInRotatedSortedArray {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int findMinBrute(int[] nums){

        int min = nums[0];

        for(int num : nums){
            min = Math.min(min, num);
        }

        return min;
    }

    /*
    -------------------------------------------------------
    Approach 2: Modified Binary Search
    -------------------------------------------------------
    */
    public static int findMinOptimal(int[] nums){

        int left = 0;
        int right = nums.length - 1;

        while(left < right){

            int mid = left + (right - left) / 2;

            // Minimum lies in right half
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }
            // Minimum lies in left half (including mid)
            else{
                right = mid;
            }
        }

        return nums[left];
    }

    // Optional testing
    public static void main(String[] args){

        int[] nums = {3,4,5,1,2};

        System.out.println(findMinOptimal(nums));

        /*
        // Brute Force
        System.out.println(findMinBrute(nums));
        */
    }
}