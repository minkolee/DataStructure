package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 这个比较简单, 先获取第一个节点当成max, 然后遍历比较即可
 */

public class EX010327 {
    public static int max(Node<Integer> node) {
        int max;
        if (node == null) {
            return 0;
        } else{
            max = node.item;

            while (node != null) {
                if (node.item > max) {
                    max = node.item;
                }
                node = node.next;
            }
        }
        return max;
    }

    public static int maxRecursion(Node<Integer> node) {
        if (node.next == null) {
            return node.item;
        }
        return maxRecursion(node.next);
    }

    public static int getMax(int a, int b) {
    }


    public static void main(String[] args) {
        Node<Integer> nodes = Node.getIntLinkedList();
        Node.walk(nodes);
        StdOut.printf("Max number is %d\n", max(nodes));
    }
}

