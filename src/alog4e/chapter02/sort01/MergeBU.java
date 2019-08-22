package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;

public class MergeBU {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        //一次性分配一个和被排序数组一样的数组
        aux = new Comparable[a.length];

        //取长度
        int N = a.length;
        //在不超过总长度的情况下的按照1,2,4..能得到的最大数字, 也就是merge一次要排的数字
        for (int size = 1; size < N; size *= 2) {

            //每一次size长度下, 从索引0开始, 每size*2的长度进行合并.
            for (int low = 0; low < N - size; low += (size * 2)) {
                //针对最后的尾部要注意, 最后一个子数组的长度要计算一下, 取实际的索引和按照计算的2倍size索引的较小值
                //由于是两两合并, 所以不用担心, 第一次循环之后, 肯定只剩一个没有排过序, 在第二次的时候, 就会被排到了.
                merge(a, low, low + size - 1, Math.min(low + size + size - 1, N - 1));
            }
        }
    }


    //将一个数组以一个索引分为两部分, 归并两部分. low ,mid ,hi 是索引
    public static void merge(Comparable[] a, int low, int mid, int hi) {
        int i = low;
        int j = mid + 1;

        //将low-hi的部分复制到临时数组中
        for (int k = low; k <= hi; k++) {
            aux[k] = a[k];
        }

        //总的个数是hi-low, 所以要执行相同次数的循环
        //将临时数组归并回到原来数组的low-hi位置
        for (int k = low; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
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

        String[] a = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        show(a);
        StdOut.println("----------------");
        MergeBU.sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
