/*
-------------------------------------------------------
Problem ID   : LC104
Title        : Maximum Depth of Binary Tree
Topic        : Tree
Pattern      : DFS, BFS
Difficulty   : Easy

Problem Summary:
Given the root of a binary tree,
return its maximum depth.

The maximum depth is the number of nodes
along the longest path from the root down
to the farthest leaf node.

Example:

        3
       / \
      9   20
         /  \
        15   7

Output: 3

-------------------------------------------------------

Approach 1: DFS (Recursive)

Idea:
- Depth = 1 + max(leftDepth, rightDepth)

Time  : O(n)
Space : O(h)
(h = tree height)

-------------------------------------------------------

Approach 2: BFS (Level Order)

Idea:
Traverse level by level.

Each completed level increases depth.

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q54_LC104_MaximumDepthOfBinaryTree {

    /*
    -------------------------------------------------------
    Tree Node
    -------------------------------------------------------
    */
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /*
    -------------------------------------------------------
    Approach 1 : DFS (Recursive)
    -------------------------------------------------------
    */
    public static int maxDepthDFS(TreeNode root) {

        if (root == null)
            return 0;

        int leftDepth = maxDepthDFS(root.left);
        int rightDepth = maxDepthDFS(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    /*
    -------------------------------------------------------
    Approach 2 : BFS (Level Order)
    -------------------------------------------------------
    */
    public static int maxDepthBFS(TreeNode root) {

        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                if (current.left != null)
                    queue.offer(current.left);

                if (current.right != null)
                    queue.offer(current.right);
            }

            depth++;
        }

        return depth;
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */
    public static void main(String[] args) {

        /*
                 3
                / \
               9   20
                  /  \
                 15   7
        */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);

        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);

        root.right.right = new TreeNode(7);

        System.out.println("DFS Depth : " + maxDepthDFS(root));

        // BFS Approach
        System.out.println("BFS Depth : " + maxDepthBFS(root));
    }
}