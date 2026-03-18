/*
-------------------------------------------------------
Problem ID   : LC19
Title        : Remove Nth Node From End of List
Topic        : Linked List
Pattern      : Two Pointer (Gap Technique)
Difficulty   : Medium

Problem Summary:
Given the head of a linked list, remove the nth node
from the end and return the updated list.

Example:
Input  : 1 → 2 → 3 → 4 → 5, n = 2
Output : 1 → 2 → 3 → 5

-------------------------------------------------------

Approach 1: Two Pass (Brute)

Idea:
- Count length
- Remove (length - n)th node

Time  : O(n)
Space : O(1)

-------------------------------------------------------

Approach 2: One Pass (Optimal)

Idea:
- Move fast pointer n steps ahead
- Then move both slow & fast together
- When fast reaches end → slow is at node before target

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

public class Q32_LC19_RemoveNthNodeFromEnd {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1: Two Pass
    -------------------------------------------------------
    */
    public static ListNode removeNthTwoPass(ListNode head, int n){

        int length = 0;
        ListNode temp = head;

        while(temp != null){
            length++;
            temp = temp.next;
        }

        // remove head
        if(n == length)
            return head.next;

        int target = length - n;
        temp = head;

        for(int i = 1; i < target; i++){
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    }

    /*
    -------------------------------------------------------
    Approach 2: One Pass (Optimal)
    -------------------------------------------------------
    */
    public static ListNode removeNthOnePass(ListNode head, int n){

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // move fast n steps ahead
        for(int i = 0; i <= n; i++){
            fast = fast.next;
        }

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        // remove node
        slow.next = slow.next.next;

        return dummy.next;
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
        head.next.next.next.next = new ListNode(5);

        head = removeNthOnePass(head, 2);

        printList(head);
    }
}