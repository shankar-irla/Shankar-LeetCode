/*
-------------------------------------------------------
Problem ID   : LC57
Title        : Insert Interval
Topic        : Intervals
Pattern      : Interval Merge
Difficulty   : Medium

Problem Summary:
You are given an array of non-overlapping intervals sorted by start time.

Insert a new interval and merge overlapping intervals if necessary.

Example:

intervals = [[1,3],[6,9]]
newInterval = [2,5]

Output:
[[1,5],[6,9]]

-------------------------------------------------------

Approach 1: Insert + Sort + Merge
- Insert new interval
- Sort intervals
- Merge overlaps

Time  : O(n log n)
Space : O(n)

Approach 2: Optimal Single Pass
- Add intervals before overlap
- Merge overlapping intervals
- Add remaining intervals

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q23_LC57_InsertInterval {

    /*
    -------------------------------------------------------
    Approach 1: Insert + Sort + Merge
    -------------------------------------------------------
    */

    public static int[][] insertBrute(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList<>();

        for (int[] interval : intervals)
            list.add(interval);

        list.add(newInterval);

        list.sort((a,b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();

        for (int[] curr : list) {

            if (merged.isEmpty() ||
                merged.get(merged.size()-1)[1] < curr[0]) {

                merged.add(curr);
            }
            else {

                merged.get(merged.size()-1)[1] =
                        Math.max(merged.get(merged.size()-1)[1], curr[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    /*
    -------------------------------------------------------
    Approach 2: Optimal Linear Merge
    -------------------------------------------------------
    */

    public static int[][] insertOptimal(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        int i = 0;

        // add intervals before overlap
        while (i < intervals.length &&
                intervals[i][1] < newInterval[0]) {

            result.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals
        while (i < intervals.length &&
                intervals[i][0] <= newInterval[1]) {

            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);

            i++;
        }

        result.add(newInterval);

        // add remaining intervals
        while (i < intervals.length) {

            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // Optional testing
    public static void main(String[] args) {

        int[][] intervals = {
                {1,3},
                {6,9}
        };

        int[] newInterval = {2,5};

        int[][] result = insertOptimal(intervals, newInterval);

        for (int[] r : result)
            System.out.println(Arrays.toString(r));
    }
}