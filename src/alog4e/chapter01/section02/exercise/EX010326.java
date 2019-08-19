package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdOut;

/**
 * 实际上就要先获取等于key的那个节点的前一个节点, 然后让其等于之后的节点的next
 */

public class EX010326 {

    public static Node<String> remove(Node<String> linkedList, String key) {
        if (linkedList == null) {
            return null;
        }

        Node<String> start = linkedList;
        Node<String> init = linkedList;


        //判断链表开头就是符合要求的节点
        //如果节点等于key, 将start指针指向下一个节点, 然后将init 指向start
        //如果start此时就是null了, 说明到了尾部, 返回null.
        //否则继续循环, 这是用于处理开头就是连续相同的key节点
        while (start.item.equals(key)) {
            start = start.next;
            init = start;
            if (start == null) {
                return null;
            }
        }

        //在上边处理完之后, start指向第一个元素不等于key的链表, 此时再开始按照逻辑处理
        //此时init和start指向的都是这个链表, start用于移动的处理这个链表, 最后返回init就是处理后的链表就可以了

        //由于第一个元素不为key ,至少第二个元素开始才是key ,所以先判断下一个元素是不是null, 是null就返回init
        //如果不是null, 判断是不是key, 如果是key, 删除掉, 然后判断下一个元素是不是还是key, 如果还是key, 就继续循环,不是key ,则start向下移动

        while (start.next != null) {
            if (start.next.item.equals(key)) {
                start.next = start.next.next;
            }
            if (start.next == null) {
                break;
            } else if (start.next.item.equals(key)) {
                continue;
            } else {
                start = start.next;
            }
        }
        return init;
    }

    public static void main(String[] args) {
        Node<String> stringNode = Node.getLinkedList();
        Node.walk(stringNode);
        StdOut.println("------");
        stringNode = remove(stringNode, "cony4");
        Node.walk(stringNode);

    }
}

