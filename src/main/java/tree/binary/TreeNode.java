package tree.binary;

import java.util.*;

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
        invertTree(treeNode.left);
        invertTree(treeNode.right);
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
        return treeNode;
    }
}

/**
 * 树的深度.
 */
class TreeDeep {

    //递归深度优先搜索做法.
    public static int treeDeep(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        //写出来之后可以转换.
        int leftDeep = treeDeep(treeNode.left);
        int rightDeep = treeDeep(treeNode.right);
        //只有在左右都遍历之后,才能知道根节点的最大深度,所以是后序遍历.
        int deep = Math.max(leftDeep, rightDeep) + 1;
        return deep;
    }

    //层次遍历广度优先搜索做法
    public static int treeDeep2(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        List<TreeNode> queue = new LinkedList() {{
            add(treeNode);
        }};
        int res = 0;

        while (!queue.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            res++;
            for (TreeNode node : queue) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            //列表替换,丢弃上面的列表,指向新的列表.这样就使用了多个队列了,不过好像也问题不大.
            queue = temp;
        }
        return res;
    }

    //层次遍历广度优先搜索做法,重复使用一个列表.
    public static int treeDeep3(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList() {{
            add(treeNode);
        }};
        int res = 0;
        List<TreeNode> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode root = queue.poll();
                temp.add(root);
            }
            //当前层级已poll,++
            res++;
            //进入下一层级准备工作
            for (TreeNode node : temp) {
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            temp.clear();
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode rootLeft = treeNode.left = new TreeNode(9);
        TreeNode rootRight = treeNode.right = new TreeNode(20);
        rootRight.left = new TreeNode(15);
        rootRight.right = new TreeNode(7);
        System.out.println(treeDeep3(treeNode));
    }
}

class BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> preMap = new HashMap<>();
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            preMap.put(preorder[i], i);
        }
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        int rootVal = preorder[i];
        Integer leftNum = inMap.get(rootVal);
        Integer rightNum = preorder.length - leftNum - 1;
        int leftVal = preorder[i + 1];
        int rightVal = preorder[i + leftNum + 1];
    }

}

class IsBalanced {
    public boolean isBalanced(TreeNode root) {

    }

    private int deepDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = deepDiff(root.left);
        if (leftDeep == -1) {
            return -1;
        }
        int rightDeep = deepDiff(root.right);
        if (rightDeep == -1) {
            return -1;
        }
        int deep = Math.max(leftDeep, rightDeep) + 1;
        if (Math.abs(leftDeep - rightDeep) <= 1) {
            return deep;
        }
        return -1;
    }
}
