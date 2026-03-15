/*
-------------------------------------------------------
Problem ID   : LC56
Title        : Merge Intervals
Topic        : Intervals
Pattern      : Sorting + Merge
Difficulty   : Medium

Problem Summary:
Given an array of intervals where intervals[i] = [start, end],
merge all overlapping intervals and return an array of the
non-overlapping intervals.

Example:
Input  : [[1,3],[2,6],[8,10],[15,18]]
Output : [[1,6],[8,10],[15,18]]

Explanation:
[1,3] and [2,6] overlap → merged into [1,6]

-------------------------------------------------------

Approach 1: Brute Force
- Compare every pair of intervals and merge if needed

Time  : O(n²)
Space : O(n)

Approach 2: Optimal (Sort + Merge)
- Sort intervals by start
- Merge overlapping intervals

Time  : O(n log n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q24_LC56_MergeIntervals {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */

    public static int[][] mergeBrute(int[][] intervals) {

        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {

            int start = intervals[i][0];
            int end = intervals[i][1];

            for (int j = i + 1; j < intervals.length; j++) {

                if (intervals[j][0] <= end) {

                    end = Math.max(end, intervals[j][1]);
                    i = j;
                }
                else
                    break;
            }

            result.add(new int[]{start,end});
        }

        return result.toArray(new int[result.size()][]);
    }

    /*
    -------------------------------------------------------
    Approach 2: Optimal Sorting + Merge
    -------------------------------------------------------
    */

    public static int[][] mergeOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        int[] current = intervals[0];
        result.add(current);

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] <= current[1]) {

                current[1] = Math.max(current[1], intervals[i][1]);
            }
            else {

                current = intervals[i];
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    // Optional testing
    public static void main(String[] args) {

        int[][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };

        int[][] result = mergeOptimal(intervals);

        for (int[] r : result)
            System.out.println(Arrays.toString(r));
    }
}