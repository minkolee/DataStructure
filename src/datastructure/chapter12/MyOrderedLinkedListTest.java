package datastructure.chapter12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class MyOrderedLinkedListTest {

    public static void main(String[] args) {
        MyOrderedLinkedList<Integer> linkedList1 = new MyOrderedLinkedList<>();

        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            linkedList1.add(i + 5);

        }

        System.out.println(Arrays.toString(linkedList1.toArray()) + "|" + linkedList1.getLength());

        System.out.println(linkedList1.contains(12));
        System.out.println(linkedList1.contains(132));
        System.out.println(linkedList1.getPosition(Integer.valueOf(7)));
        System.out.println(linkedList1.remove(Integer.valueOf(722)));

        System.out.println(Arrays.toString(linkedList1.toArray()) + "|" + linkedList1.getLength());

    }
}
