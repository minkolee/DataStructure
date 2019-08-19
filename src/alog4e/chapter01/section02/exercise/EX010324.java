package alog4e.chapter01.section02.exercise;


public class EX010324 {

    public static void removeAfter(Node node) {
        if (node == null) {
            return;
        }
        if (node.next == null) {
            return;
        } else {
            node.next = null;
        }
    }

    public static void main(String[] args) {
        Node<String> ll = Node.getLinkedList();
        Node.walk(ll);

        removeAfter(ll.next);
        Node.walk(ll);
    }
}

