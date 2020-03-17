package datastructure.chapter09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TestRadixSort {

    public static String[] getRandomNumberStringArray(int count) {
        Random random = new Random();

        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = String.valueOf(random.nextInt(count * 100 + 10));
        }

        System.out.println("生成的数字字符串数组是: " + Arrays.toString(result));
        return result;

    }

    public static void main(String[] args) {

        RadixSort.sortDesc(getRandomNumberStringArray(15));
        RadixSort.sort(getRandomNumberStringArray(15));

    }

}
