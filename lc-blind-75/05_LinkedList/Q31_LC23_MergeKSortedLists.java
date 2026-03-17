/*
-------------------------------------------------------
Problem ID   : LC23
Title        : Merge K Sorted Lists
Topic        : Linked List
Pattern      : Min Heap / Divide & Conquer
Difficulty   : Hard

Problem Summary:
You are given an array of k sorted linked lists.
Merge all lists into one sorted linked list.

Example:
Input:
[
  1→4→5,
  1→3→4,
  2→6
]

Output:
1 → 1 → 2 → 3 → 4 → 4 → 5 → 6

-------------------------------------------------------

Approach 1: Min Heap (Optimal)

Idea:
- Put all heads into a min heap
- Extract smallest node
- Add next node from that list

Time  : O(N log k)
Space : O(k)

-------------------------------------------------------

Approach 2: Divide & Conquer

Idea:
- Merge lists in pairs
- Similar to merge sort

Time  : O(N log k)
Space : O(log k)

-------------------------------------------------------
*/

import java.util.*;

public class Q31_LC23_MergeKSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1: Min Heap
    -------------------------------------------------------
    */
    public static ListNode mergeKListsHeap(ListNode[] lists){

        PriorityQueue<ListNode> pq =
                new PriorityQueue<>((a,b) -> a.val - b.val);

        // add all heads
        for(ListNode node : lists){
            if(node != null)
                pq.add(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(!pq.isEmpty()){

            ListNode smallest = pq.poll();

            tail.next = smallest;
            tail = tail.next;

            if(smallest.next != null)
                pq.add(smallest.next);
        }

        return dummy.next;
    }

    /*
    -------------------------------------------------------
    Approach 2: Divide & Conquer
    -------------------------------------------------------
    */
    public static ListNode mergeKListsDivide(ListNode[] lists){

        if(lists == null || lists.length == 0)
            return null;

        return mergeRange(lists, 0, lists.length - 1);
    }

    private static ListNode mergeRange(ListNode[] lists, int left, int right){

        if(left == right)
            return lists[left];

        int mid = (left + right) / 2;

        ListNode l1 = mergeRange(lists, left, mid);
        ListNode l2 = mergeRange(lists, mid + 1, right);

        return mergeTwo(l1, l2);
    }

    private static ListNode mergeTwo(ListNode l1, ListNode l2){

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(l1 != null && l2 != null){

            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }

            tail = tail.next;
        }

        if(l1 != null) tail.next = l1;
        if(l2 != null) tail.next = l2;

        return dummy.next;
    }

    // Optional testing
    public static void main(String[] args){

        ListNode a = new ListNode(1);
        a.next = new ListNode(4);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode c = new ListNode(2);
        c.next = new ListNode(6);

        ListNode[] lists = {a, b, c};

        ListNode result = mergeKListsHeap(lists);

        while(result != null){
            System.out.print(result.val + " -> ");
            result = result.next;
        }
    }
}