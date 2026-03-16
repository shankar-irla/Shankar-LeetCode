/*
-------------------------------------------------------
Problem ID   : LC253
Title        : Meeting Rooms II
Topic        : Intervals
Pattern      : Min Heap / Two Pointer Timeline
Difficulty   : Medium

Problem Summary:
Given an array of meeting intervals intervals[i] = [start, end],
return the minimum number of meeting rooms required.

Example:
Input  : [[0,30],[5,10],[15,20]]
Output : 2

Explanation:
Meeting [0,30] overlaps with [5,10] → need 2 rooms.

-------------------------------------------------------

Approach 1: Min Heap (Priority Queue)

Idea:
- Sort meetings by start time
- Use min heap to track earliest ending meeting
- If next meeting starts after earliest end → reuse room
- Else → allocate new room

Time  : O(n log n)
Space : O(n)

-------------------------------------------------------

Approach 2: Timeline Two Pointer

Idea:
- Separate start[] and end[]
- Sort both arrays
- Compare start with earliest end

Time  : O(n log n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q27_LC253_MeetingRoomsII {

    /*
    -------------------------------------------------------
    Approach 1: Min Heap
    -------------------------------------------------------
    */
    public static int minMeetingRoomsHeap(int[][] intervals) {

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(intervals[0][1]);

        for(int i = 1; i < intervals.length; i++){

            if(intervals[i][0] >= heap.peek()){
                heap.poll();
            }

            heap.add(intervals[i][1]);
        }

        return heap.size();
    }

    /*
    -------------------------------------------------------
    Approach 2: Timeline Two Pointer
    -------------------------------------------------------
    */
    public static int minMeetingRoomsTimeline(int[][] intervals){

        int n = intervals.length;

        int[] start = new int[n];
        int[] end = new int[n];

        for(int i = 0; i < n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;
        int e = 0;

        for(int s = 0; s < n; s++){

            if(start[s] < end[e]){
                rooms++;
            }else{
                e++;
            }
        }

        return rooms;
    }

    // Optional testing
    public static void main(String[] args){

        int[][] intervals = {
                {0,30},
                {5,10},
                {15,20}
        };

        System.out.println(minMeetingRoomsHeap(intervals));
        System.out.println(minMeetingRoomsTimeline(intervals));
    }
}