package datastructure.chapter08;

import java.util.Arrays;
import java.util.Random;

public class LinkedListSortTest {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        Random random = new Random();

        int count = 5;

        for (int j = 0; j < count; j++) {
            System.out.println("-----------------------------------------------------");
            int number = count * 2;
            for (int i = 0; i < number; i++) {
                linkedList.add(random.nextInt(number * 3 + 1));
            }

            linkedList.showAllEntries();
            if (j % 2 == 0) {
                linkedList.sort();
            } else {
                linkedList.sortDesc();
            }

            linkedList.showAllEntries();

            System.out.println("-----------------------------------------------------");

        }

        System.out.println("-----------------------------------------------------");
        for (int j = 0; j < 10; j++) {
            linkedList.add(random.nextInt(j * 3 + 1));

        }

        linkedList.showAllEntries();

        linkedList.sortDesc();

        linkedList.showAllEntries();

        linkedList.sort();

        linkedList.showAllEntries();

        linkedList.remove();
        linkedList.showAllEntries();

        System.out.println(linkedList.remove());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.remove());
        linkedList.showAllEntries();

    }
}
