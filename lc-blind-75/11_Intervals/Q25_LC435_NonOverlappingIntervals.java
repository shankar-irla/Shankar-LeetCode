/*
-------------------------------------------------------
Problem ID   : LC435
Title        : Non-Overlapping Intervals
Topic        : Intervals
Pattern      : Greedy Interval Scheduling
Difficulty   : Medium

Problem Summary:
Given an array of intervals intervals[i] = [start, end],
return the minimum number of intervals you need to remove
to make the rest of the intervals non-overlapping.

Example:
Input  : [[1,2],[2,3],[3,4],[1,3]]
Output : 1

Explanation:
Remove [1,3] to make the rest non-overlapping.

-------------------------------------------------------

Approach 1: Brute Force
- Compare all intervals and count overlaps

Time  : O(n²)
Space : O(1)

Approach 2: Greedy (Optimal)
- Sort intervals by END time
- Always keep the interval with the smallest end
- Remove intervals that overlap

Time  : O(n log n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q25_LC435_NonOverlappingIntervals {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */

    public static int eraseOverlapBrute(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        int removals = 0;

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < intervals[i-1][1]) {

                removals++;

                intervals[i][1] =
                        Math.min(intervals[i][1], intervals[i-1][1]);
            }
        }

        return removals;
    }

    /*
    -------------------------------------------------------
    Approach 2: Greedy Optimal
    -------------------------------------------------------
    */

    public static int eraseOverlapOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);

        int removals = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < prevEnd) {

                removals++;
            }
            else {

                prevEnd = intervals[i][1];
            }
        }

        return removals;
    }

    // Optional testing
    public static void main(String[] args) {

        int[][] intervals = {
                {1,2},
                {2,3},
                {3,4},
                {1,3}
        };

        System.out.println(
                eraseOverlapOptimal(intervals)
        );
    }
}