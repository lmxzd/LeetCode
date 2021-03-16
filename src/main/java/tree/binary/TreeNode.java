package tree.binary;

/**
 * @author ZhangD
 * @since 2021-03-16
 */
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

/**
 * 反转二叉树
 */
class InvertTree {
    public static TreeNode invertTree(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        treeNode.right = invertTree(treeNode.left);
        treeNode.left = invertTree(treeNode.right);
        return treeNode;
    }
}
