package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

import java.util.Random;

public class Insertion {

    public static void sort(Comparable[] comparables) {
        int N = comparables.length;
        //从数组的第二个位置开始遍历
        for (int i = 1; i < N; i++) {
            //然后使用一个循环从i当前位置反向遍历, 如果当前位置比上一个位置小, 就交换二者, 然后继续比较, 直到将小数字交换到正确位置
            for (int j = i; j > 0 && less(comparables[j], comparables[j - 1]);j--) {
                exch(comparables, j, j - 1);
            }
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
        Insertion.sort(a);
        long end = System.currentTimeMillis();
        StdOut.println("Insertion time is " + (end - start));
    }
}
