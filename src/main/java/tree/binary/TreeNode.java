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
    /**
     * 利用队列的特性.
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            //1.可以利用队列的特性去除这个for循环.
            // int size = queue.size();
            // for (int i = 0; i < size; i++) {
            //     TreeNode node = queue.poll();
            //     ans.add(node.val);
            //     if (node.left != null) {
            //         queue.add(node.left);
            //     }
            //     if (node.right != null) {
            //         queue.add(node.right);
            //     }
            // }
            //2.充分利用队列的特性.
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
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
    public static boolean isBalanced(TreeNode root) {
        return deep(root) != -1;
    }

    private static int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = deep(root.left);
        if (leftDeep == -1) {
            return -1;
        }
        int rightDeep = deep(root.right);
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
