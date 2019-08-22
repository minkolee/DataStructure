package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;

import java.util.Random;

public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        //一次性分配一个和被排序数组一样的数组
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int hi) {
        //数组排完之后应该low和hi相等,表示只有一个数字了
        if (hi <= low) {
            return;
        }
        //计算中位数, 这个公式要记住, 数组的中位数都要如此计算
        int mid = low + (hi - low) / 2;

//        StdOut.printf("此时 low = %d, mid = %d, hi = %d\n", low, mid, hi);

        //排前半段
        sort(a, low, mid);
        //排后半段
        sort(a, mid + 1, hi);
        //合并前后两半
        merge(a, low, mid, hi);
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
        Merge.sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
