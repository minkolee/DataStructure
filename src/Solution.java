/**
 * 检查对称.
 * 从最左侧深度优先遍历, 和从最右侧深度优先遍历, 如果每次值都相等, 那么就是对称的.
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);

    }


    private boolean isMirror(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        } else if (leftNode == null || rightNode == null) {
            return false;
        } else if (leftNode.val != rightNode.val) {
            return false;
        } else {
            return isMirror(leftNode.right, rightNode.left) && isMirror(leftNode.left, rightNode.right);
        }

    }
}