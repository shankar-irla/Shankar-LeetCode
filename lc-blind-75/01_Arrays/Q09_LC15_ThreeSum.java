/*
-------------------------------------------------------
Problem ID   : LC15
Title        : 3Sum
Topic        : Arrays
Pattern      : Sorting + Two Pointers
Difficulty   : Medium

Problem Summary:
Given an integer array nums, return all unique triplets
[a, b, c] such that:

a + b + c = 0

The solution set must not contain duplicate triplets.

Example:
nums = [-1,0,1,2,-1,-4]
Output = [[-1,-1,2],[-1,0,1]]

-------------------------------------------------------

Approach 1: Brute Force
- Check every possible triplet using 3 loops.
Time  : O(n³)
Space : O(1)

Approach 2: Hashing
- Fix one element
- Use HashSet to detect complements
Time  : O(n²)
Space : O(n)

Approach 3: Optimal (Sorting + Two Pointers)
- Sort the array
- Fix one element
- Use two pointers for remaining elements
Time  : O(n²)
Space : O(1) (excluding result)

-------------------------------------------------------
*/

import java.util.*;

public class Q09_LC15_ThreeSum {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static List<List<Integer>> threeSumBrute(int[] nums) {

        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {

                    if (nums[i] + nums[j] + nums[k] == 0) {

                        List<Integer> triplet =
                                Arrays.asList(nums[i], nums[j], nums[k]);

                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    /*
    -------------------------------------------------------
    Approach 2: Hashing
    -------------------------------------------------------
    */
    public static List<List<Integer>> threeSumHashing(int[] nums) {

        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            Set<Integer> seen = new HashSet<>();

            for (int j = i + 1; j < nums.length; j++) {

                int complement = -(nums[i] + nums[j]);

                if (seen.contains(complement)) {

                    List<Integer> triplet =
                            Arrays.asList(nums[i], nums[j], complement);

                    Collections.sort(triplet);
                    result.add(triplet);
                }

                seen.add(nums[j]);
            }
        }

        return new ArrayList<>(result);
    }

    /*
    -------------------------------------------------------
    Approach 3: Optimal (Sorting + Two Pointers)
    -------------------------------------------------------
    */
    public static List<List<Integer>> threeSumOptimal(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i], nums[left], nums[right]));

                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1])
                        left++;

                    while (left < right && nums[right] == nums[right + 1])
                        right--;
                }

                else if (sum < 0)
                    left++;
                else
                    right--;
            }
        }

        return result;
    }

    // Optional local testing
    public static void main(String[] args) {

        int[] nums = {-1,0,1,2,-1,-4};

        System.out.println("Brute   : " + threeSumBrute(nums));
        System.out.println("Hashing : " + threeSumHashing(nums));
        System.out.println("Optimal : " + threeSumOptimal(nums));
    }
}