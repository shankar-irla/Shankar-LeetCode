/*
-------------------------------------------------------
Problem ID   : LC572
Title        : Subtree of Another Tree
Topic        : Trees
Pattern      : DFS + Same Tree
Difficulty   : Easy

Problem Summary:
Given the roots of two binary trees root and subRoot,
return true if subRoot is a subtree of root.

A subtree of a binary tree is a node in the tree and
all of its descendants.

Example:

Root:

        3
       / \
      4   5
     / \
    1   2

SubRoot:

      4
     / \
    1   2

Output:
true

-------------------------------------------------------

Approach 1 : Brute Force DFS

Traverse every node in root.
Whenever values match, compare both trees.

Time  : O(n × m)
Space : O(h)

-------------------------------------------------------

Approach 2 : DFS + Same Tree (Optimal)

1. Traverse the main tree.
2. At each node, compare the subtree.
3. If matched, return true.

Time  : O(n × m)
Space : O(h)

-------------------------------------------------------
*/

public class Q59_LC572_SubtreeOfAnotherTree {

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
    Approach 1 & 2 : DFS + Same Tree
    -------------------------------------------------------
    */

    public static boolean isSubtree(TreeNode root, TreeNode subRoot){

        if(subRoot == null)
            return true;

        if(root == null)
            return false;

        if(isSameTree(root, subRoot))
            return true;

        return isSubtree(root.left, subRoot)
                ||
               isSubtree(root.right, subRoot);
    }

    /*
    -------------------------------------------------------
    Compare Two Trees
    -------------------------------------------------------
    */

    private static boolean isSameTree(TreeNode p, TreeNode q){

        if(p == null && q == null)
            return true;

        if(p == null || q == null)
            return false;

        if(p.val != q.val)
            return false;

        return isSameTree(p.left, q.left)
                &&
               isSameTree(p.right, q.right);
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */

    public static void main(String[] args){

        /*
                Root

                    3
                   / \
                  4   5
                 / \
                1   2
        */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        /*
                SubRoot

                  4
                 / \
                1   2
        */

        TreeNode subRoot = new TreeNode(4);

        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        System.out.println(
                isSubtree(root, subRoot)
        );
    }
}