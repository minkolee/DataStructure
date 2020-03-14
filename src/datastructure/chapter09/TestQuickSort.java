package datastructure.chapter09;

import java.util.Arrays;
import java.util.Random;

public class TestQuickSort {

    public static void fillArray(Integer[] array, int length) {
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length * 2);
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        int count = random.nextInt(20);
        Integer[] array = new Integer[count];

        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sort(array);
        System.out.println("升序全排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortDesc(array);
        System.out.println("降序全排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        int startIndex = random.nextInt(array.length);
        int endIndex = startIndex + random.nextInt(array.length / 2 + 1);
        if (endIndex > array.length - 1) {
            endIndex = array.length - 1;
        }
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortBetweenIndex(array, startIndex, endIndex);
        System.out.println("在索引 " + startIndex + " 和 " + endIndex + " 之间升序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortBetweenIndexDesc(array, startIndex, endIndex);
        System.out.println("在索引 " + startIndex + " 和 " + endIndex + " 之间降序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();

        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        int n = random.nextInt(array.length - 1 + 1);
        QuickSort.sortFromStart(array, n);
        System.out.println("对数组的前 " + n + " 个元素升序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortFromStartDesc(array, n);
        System.out.println("对数组的前 " + n + " 个元素降序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortFromEnd(array, n);
        System.out.println("对数组的后 " + n + " 个元素升序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println("----------------------------------------------");
        fillArray(array, array.length);
        System.out.println("原始数组是: \n" + Arrays.toString(array));
        QuickSort.sortFromEndDesc(array, n);
        System.out.println("对数组的后 " + n + " 个元素降序排列的结果是: \n" + Arrays.toString(array));
        System.out.println("----------------------------------------------");


    }
}
