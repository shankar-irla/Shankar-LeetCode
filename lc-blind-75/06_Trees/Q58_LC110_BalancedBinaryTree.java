/*
-------------------------------------------------------
Problem ID   : LC110
Title        : Balanced Binary Tree
Topic        : Trees
Pattern      : Bottom-Up DFS
Difficulty   : Easy

Problem Summary:
Given the root of a binary tree,
determine if it is height-balanced.

A binary tree is balanced if the heights of the
left and right subtrees of every node differ by
at most 1.

Example:

        3
       / \
      9   20
         /  \
        15   7

Output:
true

-------------------------------------------------------

Approach 1 : Brute Force

For every node:
- Calculate left height
- Calculate right height
- Recursively check children

Time  : O(n²)
Space : O(h)

-------------------------------------------------------

Approach 2 : Bottom-Up DFS (Optimal)

Return:
- Height if subtree is balanced
- -1 if subtree is unbalanced

Time  : O(n)
Space : O(h)

-------------------------------------------------------
*/

public class Q58_LC110_BalancedBinaryTree {

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
    Approach 1 : Brute Force
    -------------------------------------------------------
    */

    public static boolean isBalancedBrute(TreeNode root){

        if(root == null)
            return true;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1)
            return false;

        return isBalancedBrute(root.left)
                &&
               isBalancedBrute(root.right);
    }

    private static int height(TreeNode root){

        if(root == null)
            return 0;

        return 1 + Math.max(height(root.left),
                            height(root.right));
    }

    /*
    -------------------------------------------------------
    Approach 2 : Bottom-Up DFS (Optimal)
    -------------------------------------------------------
    */

    public static boolean isBalanced(TreeNode root){

        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode root){

        if(root == null)
            return 0;

        int leftHeight = checkHeight(root.left);

        if(leftHeight == -1)
            return -1;

        int rightHeight = checkHeight(root.right);

        if(rightHeight == -1)
            return -1;

        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */

    public static void main(String[] args){

        /*
                 3
                / \
               9   20
                  /  \
                 15   7

        Balanced = true
        */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);

        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);

        root.right.right = new TreeNode(7);

        System.out.println(
                "Optimal : " + isBalanced(root)
        );

        System.out.println(
                "Brute   : " + isBalancedBrute(root)
        );
    }
}