package alog4e.chapter02.sort01;

import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

public class Quick {
    private static int count = 1;

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int hi) {
        //数组到底了就做完了
        if (hi <= low) {
            return;
        }
        //这是关键点, partition函数做的工作是找到切分点, 然后对数组进行上边第二步的操作
        int j = partition(a, low, hi);

//        StdOut.printf("第%d次调用: low=%d, hi=%d, mid=%d ", count++, low, hi, j);
//        show(a);

        //与归并排序不同的是, 快排在进入递归前处理了一次数组
        //这里要注意的是, 切分点不要包含在数组中, 红宝书上的代码错了
        sort(a, low, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int low, int hi) {

        int i = low, j = hi + 1;
        //这里直接取了最左边的元素当成切分值
        Comparable v = a[low];

        while (true) {
            //从左边扫到右边, 如果都小于v, 扫到最左边索引结束. 如果有一个数大于v, 就结束循环, 此时i停在索引的位置
            //如果扫到一个,小于v的, 就会终止循环

            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            //右边往左边扫, 如果当前的数小于v,就停止循环, 此时停在j索引处
            while (less(v, a[--j])) {
                if (j == low) {
                    break;
                }
            }
            //每次循环检测两个指针是否相遇, 相遇了就break出外层循环
            if (i >= j) {
                break;
            }
            //没有相遇, 交换两个元素的位置
            exch(a, i, j);
        }
        //break到循环外边表示已经相遇, 交换low和j 的位置
        exch(a, low, j);
        //此时j索引的位置, 就是切分点.
        return j;
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
        Quick.sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
