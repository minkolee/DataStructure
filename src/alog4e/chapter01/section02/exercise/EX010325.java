package alog4e.chapter01.section02.exercise;

/**
 * 插入节点,
 */

public class EX010325 {

    public static void insertAfter(Node target, Node newNode) {
        if (newNode == null || target == null) {
            return;
        }

        Node temp = target.next;
        target.next = newNode;
        newNode.next = temp;
    }

    public static void main(String[] args) {
        Node<String> ll = Node.getLinkedList();
        Node newnode = new Node();
        newnode.item = "new";

        insertAfter(ll.next, newnode);
        Node.walk(ll);
    }
}

