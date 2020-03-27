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

        System.out.println(list.contains(10));
        System.out.println(list.getPosition(10));
        System.out.println(list.remove(Integer.valueOf(33)));


        System.out.println(Arrays.toString(list.toArray()) + "|" + list.getLength());

    }
}
