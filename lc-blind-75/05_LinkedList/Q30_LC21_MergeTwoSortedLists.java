/*
-------------------------------------------------------
Problem ID   : LC21
Title        : Merge Two Sorted Lists
Topic        : Linked List
Pattern      : Two Pointer Merge
Difficulty   : Easy

Problem Summary:
You are given the heads of two sorted linked lists.
Merge them into one sorted linked list and return the head.

Example:

List1: 1 → 2 → 4
List2: 1 → 3 → 4

Output:
1 → 1 → 2 → 3 → 4 → 4

-------------------------------------------------------

Approach 1: Iterative (Optimal)

Idea:
- Use a dummy node
- Compare nodes from both lists
- Attach the smaller node each time

Time  : O(n + m)
Space : O(1)

-------------------------------------------------------

Approach 2: Recursive

Idea:
- Recursively attach smaller node

Time  : O(n + m)
Space : O(n + m) recursion stack

-------------------------------------------------------
*/

public class Q30_LC21_MergeTwoSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1: Iterative
    -------------------------------------------------------
    */
    public static ListNode mergeIterative(ListNode list1, ListNode list2){

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(list1 != null && list2 != null){

            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else{
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        if(list1 != null)
            tail.next = list1;

        if(list2 != null)
            tail.next = list2;

        return dummy.next;
    }

    /*
    -------------------------------------------------------
    Approach 2: Recursive
    -------------------------------------------------------
    */
    public static ListNode mergeRecursive(ListNode l1, ListNode l2){

        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val < l2.val){

            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        }else{

            l2.next = mergeRecursive(l1, l2.next);
            return l2;
        }
    }

    // Utility: Print list
    public static void printList(ListNode head){

        while(head != null){
            System.out.print(head.val + " -> ");
            head = head.next;
        }

        System.out.println("null");
    }

    // Optional testing
    public static void main(String[] args){

        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(4);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode merged = mergeIterative(a,b);

        printList(merged);
    }
}