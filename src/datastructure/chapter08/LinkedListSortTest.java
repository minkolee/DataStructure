package datastructure.chapter08;

import java.util.Arrays;
import java.util.Random;

public class LinkedListSortTest {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        LinkedList<Integer> linkedList1 = new LinkedList<>();



        Random random = new Random();
        int count = random.nextInt(15) + 5;

        for (int i = 0; i < count; i++) {
            linkedList.add(random.nextInt(count * 5));
            linkedList1.add(random.nextInt(count * 5));
        }

        linkedList.showAllEntries();
        linkedList1.showAllEntries();

        linkedList.sortDesc();
        linkedList1.sortDesc();

        linkedList.showAllEntries();
        linkedList1.showAllEntries();

        LinkedList<Integer> result = linkedList.merge(linkedList1);

        result.showAllEntries();

        result.showLastNode();
    }
}
