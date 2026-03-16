/*
-------------------------------------------------------
Problem ID   : LC141
Title        : Linked List Cycle
Topic        : Linked List
Pattern      : Fast & Slow Pointer (Floyd Cycle Detection)
Difficulty   : Easy

Problem Summary:
Given the head of a linked list, determine if the list
contains a cycle.

Return true if there is a cycle, otherwise false.

Example:
3 → 2 → 0 → -4
      ↑     ↓
      ← ← ←

-------------------------------------------------------

Approach 1: HashSet

Idea:
- Store visited nodes
- If node repeats → cycle exists

Time  : O(n)
Space : O(n)

-------------------------------------------------------

Approach 2: Floyd's Cycle Detection (Optimal)

Idea:
- Use two pointers:
  slow → moves 1 step
  fast → moves 2 steps

If cycle exists → they will meet.

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

import java.util.HashSet;

public class Q29_LC141_LinkedListCycle {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1: HashSet
    -------------------------------------------------------
    */
    public static boolean hasCycleHash(ListNode head){

        HashSet<ListNode> visited = new HashSet<>();

        while(head != null){

            if(visited.contains(head))
                return true;

            visited.add(head);
            head = head.next;
        }

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 2: Fast & Slow Pointer (Optimal)
    -------------------------------------------------------
    */
    public static boolean hasCycle(ListNode head){

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }

        return false;
    }

    // Optional testing
    public static void main(String[] args){

        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);

        a.next = b;
        b.next = c;
        c.next = d;

        d.next = b; // creating cycle

        System.out.println(hasCycle(a));
    }
}