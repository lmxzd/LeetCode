package daily;

/**
 * @author ZhangD
 * @since 2020-08-28
 * 21/剑指offer25:合并有序链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * todo:双指针解法.
 * <p>
 * 标签:分治算法(递归思想的最基本应用)
 * 可以使用新建链表来做(空间复杂度为O(n+m)),但最好直接改变指针指向,这样就不用新创建内存空间了.
 */
public class MergeTwoLists {
    // /**
    //  * 这种方式新创建一个链表,空间复杂度高(一直在new对象).不是很好的方法.
    //  */
    // public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    //     if (l1 == null) {
    //         return l2;
    //     } else if (l2 == null) {
    //         return l1;
    //     } else {
    //         ListNode originNode = new ListNode(0);
    //         findMin(l1, l2, originNode);
    //         return originNode.next;
    //     }
    // }
    //
    // private void findMin(ListNode l1, ListNode l2, ListNode originNode) {
    //     int first;
    //     if (l1.val > l2.val) {
    //         first = l2.val;
    //         ListNode firstNode = new ListNode(first);
    //         originNode.next = firstNode;
    //         if (l2.next != null) {
    //             findMin(l1, l2.next, firstNode);
    //         }
    //         //递归终止条件.若l2为最小值,且l2为该链表最后一个节点时.
    //         else {
    //             firstNode.next = l1;
    //         }
    //     } else {
    //         first = l1.val;
    //         ListNode firstNode = new ListNode(first);
    //         originNode.next = firstNode;
    //         if (l1.next != null) {
    //             findMin(l1.next, l2, firstNode);
    //         }
    //         //递归终止条件.若l1为最小值,且l1为该链表最后一个节点时.
    //         else {
    //             firstNode.next = l2;
    //         }
    //     }
    // }

    /**
     * 官方推荐题解
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //前两个if决定递归终止条件.
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }

    /**
     * 递归转迭代,用while循环替代
     */
    public ListNode mergeTwoListsByIteration(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                prev.next = l2;
                l2 = l2.next;
            } else {
                prev.next = l1;
                l1 = l1.next;
            }
            /*
             * while循环结束之后preHead引用释放,preHead还是指向原有的-1,但是内部结构已经通过这种形式模仿指针把对象串起来形成链表.
             * 等号理解为指向最好,此外,如果while循环外层依旧需要用到preHead,就可以在while循环外声明一个引用.这样就可以既不影响原有
             * 值,也可以在while循环外用到这个值.
             */
            prev = prev.next;
        }
        //最后l1,l2只会有一个为空,不为空的作为最大值加到prev的next节点上.
        prev.next = (l1 == null) ? l2 : l1;
        return preHead.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
