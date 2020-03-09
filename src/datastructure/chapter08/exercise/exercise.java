package datastructure.chapter08.exercise;

import datastructure.chapter08.BubbleSort;
import datastructure.chapter08.InsertionSort;
import datastructure.chapter08.SelectionSort;
import datastructure.chapter08.ShellSort;

import java.util.Arrays;

public class exercise {


    public static void Ex01() {
        Integer[] array = new Integer[]{5, 7, 4, 9, 5, 6, 3};

        SelectionSort.sort(array);

    }


    public static void main(String[] args) {
//        Ex01();
//        System.out.println("------------------------------------------");
//        Ex02();
//        System.out.println("------------------------------------------");
//        Ex03();
        System.out.println("------------------------------------------");
        Ex08();
    }


    public static void Ex02() {
        Integer[] array = new Integer[]{5, 7, 4, 9, 5, 6, 3};

        InsertionSort.sort(array);

    }

    public static void Ex03() {
        Integer[] array = new Integer[]{5, 7, 4, 9, 5, 6, 3};

        ShellSort.sort(array);

    }

    public static void Ex08() {
        Integer[] array = new Integer[]{8, 2, 6, 4, 9, 7, 1};

        BubbleSort.sortDesc(array);

        System.out.println("----------------------");
        System.out.println(Arrays.toString(array));

    }
}
