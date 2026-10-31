/*
-------------------------------------------------------
Problem ID   : LC543
Title        : Diameter of Binary Tree
Topic        : Trees
Pattern      : Post-order DFS
Difficulty   : Easy

Problem Summary:
Given the root of a binary tree, return the diameter.

The diameter is the length (number of edges) of the
longest path between any two nodes.

The path may or may not pass through the root.

Example:

        1
       / \
      2   3
     / \
    4   5

Output: 3

Longest Path:
4 → 2 → 1 → 3

-------------------------------------------------------

Approach 1 : Brute Force

For every node:
- Calculate left height
- Calculate right height
- Compute diameter

Time  : O(n²)
Space : O(h)

-------------------------------------------------------

Approach 2 : Post-order DFS (Optimal)

For every node:
- Compute left height
- Compute right height
- Update maximum diameter

Return height to parent.

Time  : O(n)
Space : O(h)

-------------------------------------------------------
*/

public class Q57_LC543_DiameterOfBinaryTree {

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

    // Stores maximum diameter
    static int diameter = 0;

    /*
    -------------------------------------------------------
    Approach 1 : Brute Force
    -------------------------------------------------------
    */

    public static int diameterBrute(TreeNode root){

        if(root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int currentDiameter = leftHeight + rightHeight;

        int leftDiameter = diameterBrute(root.left);
        int rightDiameter = diameterBrute(root.right);

        return Math.max(currentDiameter,
                Math.max(leftDiameter, rightDiameter));
    }

    private static int height(TreeNode root){

        if(root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    /*
    -------------------------------------------------------
    Approach 2 : Optimal DFS
    -------------------------------------------------------
    */

    public static int diameterOfBinaryTree(TreeNode root){

        diameter = 0;

        dfs(root);

        return diameter;
    }

    private static int dfs(TreeNode root){

        if(root == null)
            return 0;

        int leftHeight = dfs(root.left);
        int rightHeight = dfs(root.right);

        diameter = Math.max(diameter,
                leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
    -------------------------------------------------------
    Driver
    -------------------------------------------------------
    */

    public static void main(String[] args){

        /*
                1
               / \
              2   3
             / \
            4   5

        Diameter = 3
        */

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(
                "Optimal : " + diameterOfBinaryTree(root)
        );

        System.out.println(
                "Brute   : " + diameterBrute(root)
        );
    }
}