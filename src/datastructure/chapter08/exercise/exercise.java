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




    public static void Ex02() {
        Integer[] array = new Integer[]{5, 7, 4, 9, 5, 6, 3};

        InsertionSort.sort(array);

    }

    public static void Ex03() {
        Integer[] array = new Integer[]{5, 7, 4, 9, 5, 6, 3};

        ShellSort.sort(array);

    }

    public static void Ex08() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 9, 8, 7};

        BubbleSort.sortDesc(array);

        System.out.println("----------------------");
        System.out.println(Arrays.toString(array));

    }

    public static <T extends Comparable<? super T>> boolean isAscending(T[] array) {
        boolean result = true;
        if (! (array.length == 0 || array.length == 1)) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
//        Ex01();
//        System.out.println("------------------------------------------");
//        Ex02();
//        System.out.println("------------------------------------------");
//        Ex03();
        System.out.println("------------------------------------------");
        Integer[] array = new Integer[]{5, 4, 5, 5};

        System.out.println(isAscending(array));

        BubbleSort.sort(array);

        System.out.println(isAscending(array));

    }

}
