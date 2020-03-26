package datastructure.chapter12;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayListTest {

    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>(1);

        list.add("djflk1");
        list.add("djflk2");
        list.add("djflk3");
        list.add("djflk4");
        list.add("djflk5");
        list.add("djflk6");
        list.add("djflk7");
        list.add("djflk8");
        list.add("djflk9");
        list.add("djflk10");
        list.add("djflk11");
        list.add("djflk12");
        list.add("djflk13");
        list.add("djflk14");
        list.add("djflk15");
        list.add(0, "nenene1");
        list.add(1, "nenene2");
        list.add(0, "nenene0");

        for (int i = 0; i < 15; i++) {
            list.add(i, String.valueOf(i) + "gugug");
        }

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.getLength());

        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(list.contains("djflk4"));
        System.out.println(list.contains("djflk16"));


        list.remove(list.getLength() - 1);
        list.remove(list.getLength() - 1);
        list.remove(list.getLength() - 1);
        list.remove(list.getLength() - 1);

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.getLength());
        System.out.println("测试迭代");
        Iterator<String> iterator = list.iterator();

        StringBuilder stringBuilder = new StringBuilder();

        iterator.forEachRemaining(stringBuilder::append);

        System.out.println(stringBuilder.toString());

        while (!list.isEmpty()) {
            list.remove(0);
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.getLength());

        iterator = list.iterator();

        stringBuilder = new StringBuilder();

        iterator.forEachRemaining(stringBuilder::append);

        System.out.println(stringBuilder.toString()+"end");

//
//        MyArrayList<String> list2 = new MyArrayList<>(1);
//        System.out.println(list2.isEmpty());
//        list2.add(0,"fdsjlk");
//        System.out.println(Arrays.toString(list2.toArray()));
//        list2.add(0,"fdslkjjlk");
//        System.out.println(Arrays.toString(list2.toArray()));
//
//        System.out.println(list2.getLength());
//        System.out.println(list2.isEmpty());

    }
}
