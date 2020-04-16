import java.util.HashMap;
import java.util.Map;

/**
 * 合并两个有序链表, 只要不断的比较两个链表的端点就行了, 然后用两个指针不断拼上就行了.
 */

class Solution3 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;

        ListNode result;

        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            result = new ListNode(l2.val);
            l2 = l2.next;
        } else if (l2 == null) {
            result = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            if (l1.val <= l2.val) {
                result = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                result = new ListNode(l2.val);
                l2 = l2.next;
            }
        }

        ListNode currentNode = result;

        //两个链表都不为空的情况下
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                currentNode.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                currentNode.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            currentNode = currentNode.next;
        }

        //有一个为空了, 把不为空的都拼上就行了
        while (l1 != null) {
            currentNode.next = new ListNode(l1.val);
            currentNode = currentNode.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            currentNode.next = new ListNode(l2.val);
            currentNode = currentNode.next;
            l2 = l2.next;
        }

        return result;
    }
}