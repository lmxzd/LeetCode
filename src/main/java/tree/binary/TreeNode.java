package tree.binary;

import java.util.*;

/**
 * 二叉树相关题目
 *
 * @author ZhangD
 * @since 2021-03-16
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 反转二叉树
 */
class InvertTree {
    /**
     * 前序遍历
     */
    public static TreeNode invertTree(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
        invertTree(treeNode.left);
        invertTree(treeNode.right);
        return treeNode;
    }
}

/**
 * 判断二叉树是否对称
 */
class Symmetric {
    /**
     * 左前序和右前序结合,并拼接字符串序列化,进行比较.自己想出来的思路.耗时较多.
     * 类似的可以用中序序列化,求序列化之后是否为回文.(也可以判断是对称二叉树)
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        StringBuilder sl = new StringBuilder();
        StringBuilder sr = new StringBuilder();
        sl = ergodicLeft(left, sl);
        sr = ergodicRight(right, sr);
        return Objects.equals(sl.toString(), sr.toString());
    }

    private static StringBuilder ergodicLeft(TreeNode treeNode, StringBuilder s) {
        if (treeNode == null) {
            return s.append(" ");
        }
        s = s.append(treeNode.val);
        StringBuilder s1 = ergodicLeft(treeNode.left, s);
        StringBuilder s2 = ergodicLeft(treeNode.right, s1);
        return s2;
    }

    private static StringBuilder ergodicRight(TreeNode treeNode, StringBuilder s) {
        if (treeNode == null) {
            return s.append(" ");
        }
        s = s.append(treeNode.val);
        StringBuilder s1 = ergodicRight(treeNode.right, s);
        StringBuilder s2 = ergodicRight(treeNode.left, s1);
        return s2;
    }

    public boolean isSymmetric2(TreeNode root) {
        //三目运算的简化版
        return root == null || recur(root.left, root.right);
    }

    //其实也是二叉树的一种遍历.两边往中间夹的感觉.而且是两个根节点的夹逼
    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) {
            return true;
        }
        //这里还隐藏着一个条件.L==null 时 R!=null R同理
        if (L == null || R == null || L.val != R.val) {
            return false;
        }
        return recur(L.left, R.right) && recur(L.right, R.left);
    }
}

/**
 * 二叉树的层次遍历
 */
class LevelOrder {
    public int[] levelOrder(TreeNode root) {
        if(root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<TreeNode>(){{ add(root); }};
        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}