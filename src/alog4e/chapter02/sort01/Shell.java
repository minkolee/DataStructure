package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;

import java.util.Random;

public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;

        //h是组数
        int h = 1;
        //这个实际上是如何分配组数来的, 循环执行完之后, 会得到不超过N的最大分组数量
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        //这个循环就是不断缩小组数也就是h的数, 最后h=1的时候就是对整个数组排序, 之后控制h /= 3, 就会结束循环
        while (h >= 1) {
            //从分组的地方开始, 而不是从头开始
            for (int i = h; i < N; i++) {
                //以h为间隔, 从索引h开始到最后一个元素, 不断的反向判断和交换间隔为h的元素
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
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
        int N = 1000;
        Double[] a = new Double[N];
        Random random = new Random();
        //进行T次, 数组长度为N的排序, 将每次排序的时间累加到total上
        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble() + random.nextInt(100000);
        }
        show(a);

        long start = System.currentTimeMillis();
        Shell.sort(a);
        long end = System.currentTimeMillis();
        StdOut.println("Shell time is " + (end - start));
        show(a);

        StdOut.println(isSorted(a));

//        String[] a = new String[]{"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
//        show(a);
//        StdOut.println("----------------");
//        Shell.sort(a);
//        StdOut.println(isSorted(a));
    }
}
