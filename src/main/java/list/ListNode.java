package list;

/**
 * @author ZhangD
 * @since 2021-03-15
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

/**
 * 反转链表(完全反转单链表) head不为null
 */
class ReverseList0 {
    //递归做法
    public static ListNode reverse(ListNode head) {
        //baseCase
        if (head.next == null) {
            return head;
        }
        //反转下一节点,返回头结点.
        ListNode last = reverse(head.next);
        //反转当前节点.
        head.next.next = head;
        head.next = null;
        return last;
    }

    //迭代做法
    public static ListNode reverse2(ListNode head) {
        //引入cur游标
        ListNode cur = head;
        ListNode bef = null;
        ListNode nxt;
        while (cur != null) {
            //三个游标都要往后平移.而反转只有一步.主要是移动的顺序,何时修改指向.很关键.先修改不会影响后续的指向.
            nxt = cur.next;
            cur.next = bef;
            bef = cur;
            cur = nxt;
        }
        return bef;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(8);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode result = reverse2(listNode1);
        System.out.println(result);
    }
}

/**
 * 反转链表(反转单链表的头到某个节点n),n从1开始 head不为null
 */
class ReverseList1 {
    //后驱节点.
    static ListNode i;
    static ListNode end;

    //递归做法
    public static ListNode reverse(ListNode head, int n) {
        //baseCase,
        if (n == 1) {
            //记住后驱节点.
            i = head.next;
            return head;
        }
        //反转下一节点,并返回头结点
        ListNode last = reverse(head.next, n - 1);
        //反转当前节点.
        head.next.next = head;
        head.next = i;
        return last;
    }

    /**
     * 找到链表第n+1个节点的值,并赋值成员变量
     *
     * @param head 链表
     * @param n
     */
    private static void findNNxt(ListNode head, int n) {
        end = head;
        for (int i = 0; i < n; i++) {
            end = head.next;
            head = end;
        }
    }

    //迭代做法
    public static ListNode reverse2(ListNode head) {
        //引入cur游标
        ListNode cur = head;
        //引入成员变量
        ListNode bef = end;
        ListNode nxt;
        while (cur != end) {
            //三个游标都要往后平移.而反转只有一步.主要是移动的顺序,何时修改指向.很关键.先修改不会影响后续的指向.
            nxt = cur.next;
            cur.next = bef;
            bef = cur;
            cur = nxt;
        }
        return bef;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(8);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        // ListNode result = reverse(listNode1,4);
        findNNxt(listNode1, 3);
        ListNode result = reverse2(listNode1);
        System.out.println(result);
    }
}

/**
 * 反转链表(反转单链表的m到n节点) head不为null
 */
class ReverseList2 {
    static ListNode i;

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        //对于head而言,是从left->right的反转,而对于head.next来说就是left-1->right-1坐标的反转.
        //这个递归是遍历元素直到缩小区间到满足basecase,还是那句话,不要跳入递归,去分析递归的规律
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public static ListNode reverseN(ListNode head, int n) {
        //baseCase,
        if (n == 1) {
            //记住后驱节点.
            i = head.next;
            return head;
        }
        //
        ListNode last = reverseN(head.next, n - 1);
        //反转当前节点.
        head.next.next = head;
        head.next = i;
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(8);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode result = reverseBetween(listNode1, 2, 3);
        System.out.println(result);
    }
}

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
class ReverseList3 {
    /**
     * 递归反转1->n,但不拼接n之后的值,递归对于n索引是方便的,迭代对于值是方便的,但都可以相互转换.
     */
    private static ListNode reverse(ListNode head, int n) {
        //baseCase,
        if (n == 1) {
            return head;
        }
        //反转下一节点,并返回头结点
        ListNode last = reverse(head.next, n - 1);
        //反转当前节点.
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 1.递归算法
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        //新建一个引用,确保head在循环中不变
        ListNode a = head;
        //baseCase,循环n次,若小于n,则返回当前头,不反转.
        for (int i = 0; i < k; i++) {
            if (a == null) {
                return head;
            }
            a = a.next;
        }
        ListNode last = reverse(head, k);
        head.next = reverseKGroup(a, k);
        return last;
    }

    /**
     * 迭代反转前n个值,并且不拼接n之后的值
     */
    private static ListNode reverse2(ListNode head, int k) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt;
        if (k == 1) {
            return head;
        }
        for (int i = k; i > 0; i--) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 半递归,半迭代 压栈用size/n个栈,每个栈用n个空间,所以空间复杂度是O(1),递归里面的迭代.也不一定就是n*n
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        //新建一个引用,确保head在循环中不变
        ListNode a = head;
        //baseCase,循环n次,若小于n,则返回当前头,不反转.
        for (int i = 0; i < k; i++) {
            if (a == null) {
                return head;
            }
            a = a.next;
        }
        ListNode last = reverse2(head, k);
        //递归要考虑递归的结果用谁来接收,也就是前一层由谁指向递归的结果.
        head.next = reverseKGroup2(a, k);
        return last;
    }

    /**
     * 迭代采用节点的方式,反转区间是[head,last),并且不拼接n之后的值
     */
    private static ListNode reverse3(ListNode head, ListNode last) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt;
        while (cur != last) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 半递归,半迭代 压栈用size/n个栈,每个栈用n个空间,所以空间复杂度是O(1),递归里面的迭代.也不一定就是n*n
     */
    public static ListNode reverseKGroup3(ListNode head, int k) {
        //新建一个引用,确保head在循环中不变
        ListNode a = head;
        //baseCase,循环n次,若小于n,则返回当前头,不反转.
        for (int i = 0; i < k; i++) {
            if (a == null) {
                return head;
            }
            a = a.next;
        }
        ListNode last = reverse3(head, a);
        //递归要考虑递归的结果用谁来接收,也就是前一层由谁指向递归的结果.
        head.next = reverseKGroup3(a, k);
        return last;
    }

    /**
     * 递归采用节点的方式,反转区间是[head,last),并且不拼接n之后的值
     */
    private static ListNode reverse4(ListNode head, ListNode last) {
        if (head.next == last) {
            return head;
        }
        ListNode result = reverse4(head.next, last);
        head.next.next = head;
        head.next = null;
        return result;
    }

    public static ListNode reverseKGroup4(ListNode head, int k) {
        //新建一个引用,确保head在循环中不变
        ListNode a = head;
        //baseCase,循环n次,若小于n,则返回当前头,不反转.
        for (int i = 0; i < k; i++) {
            if (a == null) {
                return head;
            }
            a = a.next;
        }
        ListNode last = reverse4(head, a);
        //递归要考虑递归的结果用谁来接收,也就是前一层由谁指向递归的结果.
        head.next = reverseKGroup4(a, k);
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(8);
        ListNode listNode5 = new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        // ListNode result = reverseKGroup(listNode1, 2);
        // ListNode result = reverseKGroup2(listNode1, 2);
        // ListNode result = reverseKGroup3(listNode1, 2);
        ListNode result = reverseKGroup4(listNode1, 2);
        System.out.println(result);
    }
}

/**
 * 请判断一个链表是否为回文链表。
 */
class Palindrome {
    static ListNode left;

    public static boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    /**
     * 后序遍历模拟双指针.
     */
    private static boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && right.val == left.val;
        left = left.next;
        return res;
    }

    /**
     * 快慢指针找中点
     */
    public static boolean isPalindrome2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //找到反转前的指针
        /**
         * 快慢指针找中点
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /**
         * 如为奇数节点,则慢指针后移一个节点.
         */
        if (fast != null) {
            slow = slow.next;
        }
        ListNode reverse = reverse(slow);
        ListNode template = reverse;
        //因为reverse可能只有一个值.所以对reverse进行判断.
        while (reverse != null) {
            if (reverse.val == head.val) {
                reverse = reverse.next;
                head = head.next;
            } else {
                return false;
            }
        }
        reverse(template);
        return true;
    }

    private static ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        // ListNode listNode3 = new ListNode(7);
        // ListNode listNode4 = new ListNode(6);
        // ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        // listNode2.next = listNode3;
        // listNode3.next = listNode4;
        // listNode4.next = listNode5;
        // boolean result = isPalindrome(listNode1);
        boolean result = isPalindrome2(listNode1);
        System.out.println(result);
        System.out.println(listNode1);
    }
}