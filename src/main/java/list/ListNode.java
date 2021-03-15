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