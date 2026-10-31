/*
-------------------------------------------------------
Problem ID   : LC226
Title        : Invert Binary Tree
Topic        : Trees
Pattern      : DFS, BFS
Difficulty   : Easy

Problem Summary:
Given the root of a binary tree,
invert the tree and return its root.

Example:

Original Tree:

        4
      /   \
     2     7
    / \   / \
   1   3 6   9

Inverted Tree:

        4
      /   \
     7     2
    / \   / \
   9   6 3   1

-------------------------------------------------------

Approach 1 : DFS (Recursive)

Swap left and right subtree recursively.

Time  : O(n)
Space : O(h)

-------------------------------------------------------

Approach 2 : BFS (Queue)

Traverse level by level and swap children.

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q56_LC226_InvertBinaryTree {

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

    public static TreeNode invertTreeDFS(TreeNode root){

        if(root == null)
            return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeDFS(root.left);
        invertTreeDFS(root.right);

        return root;
    }

    /*
    -------------------------------------------------------
    Approach 2 : BFS
    -------------------------------------------------------
    */

    public static TreeNode invertTreeBFS(TreeNode root){

        if(root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            TreeNode current = queue.poll();

            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            if(current.left != null)
                queue.offer(current.left);

            if(current.right != null)
                queue.offer(current.right);
        }

        return root;
    }

    /*
    -------------------------------------------------------
    Utility : Inorder Traversal
    -------------------------------------------------------
    */

    public static void inorder(TreeNode root){

        if(root == null)
            return;

        inorder(root.left);

        System.out.print(root.val + " ");

        inorder(root.right);
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */

    public static void main(String[] args){

        /*
                  4
                /   \
               2     7
              / \   / \
             1   3 6   9
        */

        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.print("Original (Inorder): ");
        inorder(root);

        invertTreeDFS(root);

        System.out.print("\nInverted (DFS): ");
        inorder(root);

        /*
        // Uncomment to test BFS separately
        invertTreeBFS(root);
        */
    }
}