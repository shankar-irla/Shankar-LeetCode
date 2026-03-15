/*
-------------------------------------------------------
Problem ID   : LC252
Title        : Meeting Rooms
Topic        : Intervals
Pattern      : Sorting + Overlap Detection
Difficulty   : Easy

Problem Summary:
Given an array of meeting intervals where
intervals[i] = [start, end], determine if a person
can attend all meetings.

Return true if no meetings overlap.

Example:
Input  : [[0,30],[5,10],[15,20]]
Output : false

Explanation:
Meeting [0,30] overlaps with [5,10].

-------------------------------------------------------

Approach 1: Brute Force
- Compare every pair of intervals

Time  : O(n²)
Space : O(1)

Approach 2: Optimal (Sorting)
- Sort intervals by start time
- Check if current start < previous end

Time  : O(n log n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.*;

public class Q26_LC252_MeetingRooms {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */

    public static boolean canAttendBrute(int[][] intervals) {

        for (int i = 0; i < intervals.length; i++) {

            for (int j = i + 1; j < intervals.length; j++) {

                if (intervals[i][0] < intervals[j][1] &&
                    intervals[j][0] < intervals[i][1]) {

                    return false;
                }
            }
        }

        return true;
    }

    /*
    -------------------------------------------------------
    Approach 2: Optimal Sorting
    -------------------------------------------------------
    */

    public static boolean canAttendOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < intervals[i-1][1])
                return false;
        }

        return true;
    }

    // Optional testing
    public static void main(String[] args) {

        int[][] intervals = {
                {0,30},
                {5,10},
                {15,20}
        };

        System.out.println(
                canAttendOptimal(intervals)
        );
    }
}