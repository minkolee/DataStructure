package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 仅知道链表的开头是first
 * 删除尾节点, 遍历到尾节点的上一个节点即可, 然后将该节点的next设置为null.
 * 要判断上一个节点, 则需要判断x.next.next==null
 */

public class EX010319 {

    public static Node deleteLastNode(Node first) {
        Node start = first;
        if (start.next == null) {
            first = null;
            return first;
        }
        else{
            while (start.next.next != null) {
                start = start.next;
            }
            start.next = null;
            return first;
        }
    }

    public static void main(String[] args) {
        Node<String> first = new Node<>();
        first.item = "1";

        Node<String> second = new Node<>();
        second.item = "2";
        first.next = second;

        Node<String> third = new Node<>();
        third.item = "3";
        second.next = third;

        Node<String> pointer;

        pointer = first;
        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }
        StdOut.println("-0--------------");

        pointer = deleteLastNode(first);

        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }
        StdOut.println("-0--------------");
        pointer = deleteLastNode(first);

        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }
        StdOut.println("-0--------------");
        pointer = deleteLastNode(first);

        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }


    }

}

