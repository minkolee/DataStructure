package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

public class Histogram {

    public static int[] histogram(int[] array, int m) {
        int[] result = new int[m];
        int i;
        for (i = 0; i < array.length; i++) {
            result[array[i]]++;
        }
        StdOut.println(Arrays.toString(result));

        int sum = 0;

        for (i = 0; i < result.length; i++) {
            sum += result[i];
        }
        StdOut.println("Length of array is " + array.length + "\nsum of result is " + sum);
        return result;
    }

    public static void main(String[] args) {

        int arg[] = {1, 3, 4, 3, 2, 1, 3, 4, 5, 6, 7, 8, 5, 3, 3, 22, 4, 3, 54, 56, 6, 7, 8, 8, 8};
        histogram(arg, 57);
    }
}
