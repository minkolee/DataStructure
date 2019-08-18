package alog4e.chapter01.section02;

import alog4e.libs.Interval1D;
import alog4e.libs.Point2D;
import alog4e.libs.StdOut;

import java.util.Random;

public class EX010202 {

    public static void main(String[] args) {
        //生成N个间隔, 放入一个数组中
        int N = 10, i;
        Interval1D[] intervals = new Interval1D[N];

        Random random = new Random();
        for (i = 0; i < N; i++) {
            Interval1D interval = new Interval1D(random.nextDouble(), random.nextDouble() + 1);
            intervals[i] = interval;
        }

        //同样还是嵌套循环
        int j;
        for (i = 0; i < N; i++) {
            for (j = i; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (intervals[i].intersects(intervals[j])) {
                    StdOut.printf("%s %s\n", intervals[i].toString(), intervals[j].toString());
                }
            }
        }
    }

    //静态计算两点距离的函数
}
