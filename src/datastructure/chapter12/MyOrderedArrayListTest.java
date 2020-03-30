package datastructure.chapter12;

import java.util.Arrays;
import java.util.Random;

public class MyOrderedArrayListTest {

    public static void main(String[] args) {

        MyOrderedArrayList<Integer> list = new MyOrderedArrayList<>();


        for (int i = 0; i < 15; i++) {
            list.add(i * 2);
        }

        System.out.println(Arrays.toString(list.toArray()) + "|" + list.getLength());

        for (int i = 0; i < 15; i++) {
            System.out.println(list.contains(i*2));
            System.out.println(list.getPosition(i * 2));
        }
        System.out.println(list.contains(1));
        System.out.println(list.getPosition(1));
        System.out.println(list.contains(3));
        System.out.println(list.getPosition(3));
        System.out.println(list.contains(5));
        System.out.println(list.getPosition(5));


        System.out.println(Arrays.toString(list.toArray()) + "|" + list.getLength());

    }
}
