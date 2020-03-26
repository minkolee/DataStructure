package datastructure.chapter12;

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


        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        linkedList1.add(0, "start");
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());
        System.out.println("------------------------------------");
        System.out.println(linkedList1.remove(0));
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());
        System.out.println(linkedList1.remove(0));
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        linkedList1.add("vjlkjlk");
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        linkedList1.replace(0, "gugugu");
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        linkedList1.add(1, "newnwen");
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        System.out.println(linkedList1.replace(0, "123")); ;
        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        System.out.println(linkedList1.getEntry(2));

        System.out.println(linkedList1.getEntry(3));
        System.out.println(linkedList1.contains("421"));

        System.out.println(Arrays.toString(linkedList1.toArray()) + " | " + linkedList1.getLength());

        linkedList1.iterator().forEachRemaining(s -> System.out.println(s.charAt(0)));

        ListIterator listIterator = new ListIterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Object previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Object o) {

            }

            @Override
            public void add(Object o) {

            }
        };

        LinkedList<String> arrayList = new LinkedList<>();
    }
}
