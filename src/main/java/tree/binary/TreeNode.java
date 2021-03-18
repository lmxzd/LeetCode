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

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
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

/**
 * 1.首先判断是分治,分析题意写逻辑(最为复杂)
 * 2.然后有左子树,右子树,会列出树状递归.分析应该在树状递归何处(前,中,后)进行剩余的逻辑.
 */
class BuildTree {
    static Map<Integer, Integer> inMap = new HashMap<>();
    static Map<Integer, Integer> preMap = new HashMap<>();


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        for (int i = 0; i < preorder.length; i++) {
            preMap.put(i, preorder[i]);
        }
        return tree(0, 0, inorder.length - 1);
    }

    private static TreeNode tree(int root, int left, int right) {
        if (left > right) {
            return null;
        }
        //获取根节点在中序遍历数组的位置.
        Integer i = inMap.get(preMap.get(root));
        //这实际上也是一棵树,只不过这个树的根节点做的事情比较复杂.
        //前序创建对象.
        TreeNode rootNode = new TreeNode(preMap.get(root));
        TreeNode leftNode = tree(root + 1, left, i - 1);
        TreeNode rightNode = tree(root + 1 + i - left, i + 1, right);
        //后序指针指向.
        rootNode.left = leftNode;
        rootNode.right = rightNode;
        return rootNode;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
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
