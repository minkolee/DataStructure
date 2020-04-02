package datastructure.chapter12;

import java.lang.reflect.Array;
import java.util.*;

public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<String> linkedList1 = new MyLinkedList<>();

        linkedList1.add("1st");
        linkedList1.add("2nd");
        linkedList1.add("3rd");
        linkedList1.add("4th");
        linkedList1.add("5th");
        linkedList1.add("6th");

        int position;
        System.out.println(Arrays.toString(linkedList1.toArray()));
//        position = linkedList1.getPosition("4th");
//        System.out.println(position);
//
//        linkedList1.remove(position);
//        System.out.println(Arrays.toString(linkedList1.toArray()));
//
//        linkedList1.add(0,"fdkja");
//        System.out.println(Arrays.toString(linkedList1.toArray()));

        System.out.println(linkedList1.getPosition("5th"));


    }
}
