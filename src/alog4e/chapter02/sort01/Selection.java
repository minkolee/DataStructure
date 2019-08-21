package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

import java.util.Random;

public class Selection {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }


    }

    //比较两个元素大小
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换两个元素位置
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //打印元素
    private static void show(Comparable[] comparables) {
        for (Comparable comparable : comparables) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
    }

    //检测是否有序
    public static boolean isSorted(Comparable[] comparables) {
        for (int i = 1; i < comparables.length; i++) {
            if (less(comparables[i], comparables[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 270;
        Double[] a = new Double[N];
        Random random = new Random();
        //进行T次, 数组长度为N的排序, 将每次排序的时间累加到total上
        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble() + random.nextInt(100000);
        }
        show(a);

        long start = System.currentTimeMillis();
        Selection.sort(a);
        long end = System.currentTimeMillis();
        StdOut.println("Selection time is " + (end - start));

    }
}
