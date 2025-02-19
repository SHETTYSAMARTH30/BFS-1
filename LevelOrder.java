//TC : O(n)
//SC : O(h) == O(n) : recursive stack
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        //Depth first seach technique
        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;
        
        //root node will be at level = 0
        //result is call by ref. so we can also put it as global variable
        helper(root, 0, result);
        return result;
    }

    //It will be preoorder dfs, or else the values will not stored based on levels:- 0 to h
    public void helper(TreeNode root, int level, List<List<Integer>> result) {

        if(root == null)
            return;
        
        //if result size is equal to level that means the node is first in its level
        if(result.size() == level) {
            result.add(new ArrayList<>());
        }

        //there is a list already present for that level, so just get the list and add at end
        result.get(level).add(root.val);

        helper(root.left, level + 1, result);
        helper(root.right, level + 1, result);
    }
}