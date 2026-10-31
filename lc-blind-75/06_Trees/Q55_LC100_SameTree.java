/*
-------------------------------------------------------
Problem ID   : LC100
Title        : Same Tree
Topic        : Trees
Pattern      : DFS, BFS
Difficulty   : Easy

Problem Summary:
Given the roots of two binary trees p and q,
return true if they are the same.

Two trees are considered the same if:
1. They are structurally identical.
2. Corresponding nodes have the same values.

Example:

      1              1
     / \            / \
    2   3          2   3

Output:
true

-------------------------------------------------------

Approach 1 : DFS (Recursive)

Compare:
- Current node values
- Left subtrees
- Right subtrees

Time  : O(n)
Space : O(h)

-------------------------------------------------------

Approach 2 : BFS (Iterative)

Traverse both trees simultaneously.

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q55_LC100_SameTree {

    /*
    -------------------------------------------------------
    Tree Node
    -------------------------------------------------------
    */

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1 : Recursive DFS
    -------------------------------------------------------
    */

    public static boolean isSameTreeDFS(TreeNode p, TreeNode q){

        if(p == null && q == null)
            return true;

        if(p == null || q == null)
            return false;

        if(p.val != q.val)
            return false;

        return isSameTreeDFS(p.left, q.left)
                &&
               isSameTreeDFS(p.right, q.right);
    }

    /*
    -------------------------------------------------------
    Approach 2 : BFS (Queue)
    -------------------------------------------------------
    */

    public static boolean isSameTreeBFS(TreeNode p, TreeNode q){

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);

        while(!queue1.isEmpty() && !queue2.isEmpty()){

            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            if(node1 == null && node2 == null)
                continue;

            if(node1 == null || node2 == null)
                return false;

            if(node1.val != node2.val)
                return false;

            queue1.offer(node1.left);
            queue1.offer(node1.right);

            queue2.offer(node2.left);
            queue2.offer(node2.right);
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */

    public static void main(String[] args){

        /*
                Tree 1

                   1
                  / \
                 2   3
        */

        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        /*
                Tree 2

                   1
                  / \
                 2   3
        */

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        System.out.println("DFS : " + isSameTreeDFS(p, q));

        System.out.println("BFS : " + isSameTreeBFS(p, q));
    }
}