package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 仅知道链表的开头是first
 * 删除尾节点, 遍历到尾节点的上一个节点即可, 然后将该节点的next设置为null.
 * 要判断上一个节点, 则需要判断x.next.next==null
 */

public class EX010320 {

    public static Node<String> deleteNode(int k, Node<String> node) {
        //删除第K个元素, 需要将其上一个节点的next指向其下一个节点. 因此需要获取两个节点, 即上一个节点和第K个节点
        if (k <= 0) {
            throw new RuntimeException("Index must be greater than 0");
        }

        if (node == null) {
            return null;
        }
        if (k == 1) {
            return node.next;
        }

        Node current = node;
        Node lastone = node;
        for (int i = 0; i < k - 2; i++) {
            current = current.next;
            if (current == null) {
                throw new RuntimeException("There is no such element");
            }
        }
        lastone = current;
        current = current.next;
        if (current == null) {
            throw new RuntimeException("There is no such element");
        }
        lastone.next = current.next;
        return node;
    }

    public static void main(String[] args) {
        Node<String> n1 = new Node<>();
        n1.item = "cony1";
        Node<String> n2 = new Node<>();
        n2.item = "cony2";
        n1.next = n2;
        Node<String> n3 = new Node<>();
        n3.item = "cony3";
        n2.next = n3;
        Node<String> n4 = new Node<>();
        n4.item = "cony4";
        n3.next = n4;

        Node<String> pointer = n1;
        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }
        StdOut.println("------------");
        n1 = deleteNode(5, n1);
        pointer = n1;
        while (pointer != null) {
            StdOut.println(pointer);
            pointer = pointer.next;
        }
    }
}

