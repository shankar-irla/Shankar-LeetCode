/*
-------------------------------------------------------
Q01 - LC01 - Two Sum
Topic: Arrays
Pattern: HashMap Complement Pattern
Difficulty: Easy

Problem:
Given an integer array nums and an integer target,
return the indices of the two numbers such that they add up to target.

Constraints:
- Exactly one solution exists.
- The same element cannot be used twice.

Approach:
1. Traverse the array once.
2. For each element, compute:
      complement = target - nums[i]
3. If complement exists in HashMap → solution found.
4. Otherwise, store current value and index.

Time Complexity: O(n)
Space Complexity: O(n)
-------------------------------------------------------
*/

import java.util.HashMap;

public class Q01_LC01_TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            Integer index = map.get(complement);
            if (index != null) {
                return new int[]{index, i};
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }

    // Optional local testing
    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        if (result.length == 2) {
            System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
        } else {
            System.out.println("No valid pair found.");
        }
    }
}