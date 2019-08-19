package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 遍历链表搜索, 搜到就返回true ,否则返回false
 */

public class EX010321 {

    public static boolean find(String key, Node<String> linkedList) {
        boolean found = false;
        while (linkedList != null) {
            if (linkedList.item.equals(key)) {
                found = true;
                break;
            } else {
                linkedList = linkedList.next;
            }
        }
        return found;
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

        StdOut.println(find("cony5", n1));
    }
}

