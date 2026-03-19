/*
-------------------------------------------------------
Problem ID   : LC143
Title        : Reorder List
Topic        : Linked List
Pattern      : Find Middle + Reverse + Merge
Difficulty   : Medium

Problem Summary:
Given a linked list:

L0 → L1 → L2 → ... → Ln

Reorder it to:

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 ...

Modify in-place (no extra space).

Example:
Input  : 1 → 2 → 3 → 4 → 5
Output : 1 → 5 → 2 → 4 → 3

-------------------------------------------------------

Approach:
1. Find middle using slow-fast pointer
2. Reverse second half
3. Merge both halves alternately

Time  : O(n)
Space : O(1)

-------------------------------------------------------
*/

public class Q33_LC143_ReorderList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    public static void reorderList(ListNode head){

        if(head == null || head.next == null)
            return;

        // Step 1: Find middle
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: Merge two halves
        ListNode first = head;
        ListNode second = prev;

        while(second != null){

            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
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

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reorderList(head);

        printList(head);
    }
}