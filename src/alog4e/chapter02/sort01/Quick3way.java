package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

import java.util.Random;

public class Quick3way {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int hi) {
        if (hi <= low) {
            return;
        }
        int lt = low, i = low + 1, gt = hi;
        Comparable v = a[low];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, gt--, i);
            }
            else {
                i++;
            }
        }
        //递归对剩下两部分调用
        sort(a, low, lt - 1);
        sort(a, gt + 1, hi);
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

//        String[] a = new String[]{"R", "B", "W", "W", "R", "W", "B", "R", "R", "W", "B", "R", "W", "B", "R", "R"};
//        show(a);
//        StdOut.println("----------------");
//        Quick3way.sort(a);
//        show(a);
//        StdOut.println(isSorted(a));

        int N = 100;
        Double[] b = new Double[N];
        Random random = new Random();
        //进行T次, 数组长度为N的排序, 将每次排序的时间累加到total上
        for (int i = 0; i < N; i++) {
            b[i] = random.nextDouble() + random.nextInt(100000);
        }
        show(b);

        long start = System.currentTimeMillis();
        Quick3way.sort(b);
        long end = System.currentTimeMillis();
        StdOut.println("Selection time is " + (end - start));
        show(b);
        StdOut.println(isSorted(b));
    }
}
