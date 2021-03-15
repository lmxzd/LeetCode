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
 * 反转链表(完全反转单链表)
 */
class ReverseList0 {
    //递归做法
    public static ListNode reverse(ListNode head) {
        //baseCase
        if (head.next == null) {
            return head;
        }
        //反转下一节点
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
 * 反转链表(反转单链表的头到某个节点n)
 */
class ReverseList1 {
    static ListNode i;
    //递归做法
    public static ListNode reverse(ListNode head, int n) {
        //baseCase,
        if (n == 1) {
            i = head.next;
            return head;
        }
        //
        ListNode last = reverse(head.next, n - 1);
        //反转当前节点.
        head.next.next = head;
        head.next = i;
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

}

/**
 * 反转链表(反转单链表的m到n节点)
 */
class ReverseList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {

    }

    private ListNode reverseN(ListNode head, int n) {

    }
}