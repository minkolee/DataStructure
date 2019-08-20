package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 反转链表, 如果要反转一个链表, 首先需要遍历链表, 然后将链表拆成第一个节点和之后的节点, 然后将节点挂到一个新链表上去.
 */

public class EX010330 {
    public static Node reverseLinkedList(Node linkedList) {
        if (linkedList == null) {
            return null;
        }
        if (linkedList.next == null) {
            return linkedList;
        }

        Node result = null;

        Node first = linkedList;
        Node second = linkedList.next;

        //把每个first节点插入到result链表的头部中
        while (second != null) {
            //把first插入到result的头部, 此时second保留着对剩余链表的引用
            first.next = result;
            result = first;
            first = second;
            second = second.next;
        }
        //循环结束的时候, first的节点还没有插入到result中, 再插一次:
        first.next = result;
        result = first;
        return result;
    }

    public static void main(String[] args) {
        Node<Integer> nodes = Node.getIntLinkedList(5);
        Node.walk(nodes);
        StdOut.println("-----------------");
        nodes = reverseLinkedList(nodes);
        Node.walk(nodes);
    }
}

