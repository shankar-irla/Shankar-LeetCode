/*
-------------------------------------------------------
Problem ID   : LC206
Title        : Reverse Linked List
Topic        : Linked List
Pattern      : Pointer Reversal
Difficulty   : Easy

Problem Summary:
Given the head of a singly linked list,
reverse the list and return the new head.

Example:
Input  : 1 → 2 → 3 → 4 → 5
Output : 5 → 4 → 3 → 2 → 1

-------------------------------------------------------

Approach 1: Iterative (Optimal)

Idea:
- Maintain three pointers
  prev → previous node
  curr → current node
  next → next node

- Reverse link one by one.

Time  : O(n)
Space : O(1)

-------------------------------------------------------

Approach 2: Recursive

Idea:
- Reverse rest of the list
- Fix current node at end

Time  : O(n)
Space : O(n) recursion stack

-------------------------------------------------------
*/

public class Q28_LC206_ReverseLinkedList {

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
    Approach 1: Iterative
    -------------------------------------------------------
    */
    public static ListNode reverseIterative(ListNode head){

        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){

            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    /*
    -------------------------------------------------------
    Approach 2: Recursive
    -------------------------------------------------------
    */
    public static ListNode reverseRecursive(ListNode head){

        if(head == null || head.next == null)
            return head;

        ListNode newHead = reverseRecursive(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
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

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        printList(head);

        head = reverseIterative(head);

        printList(head);
    }
}