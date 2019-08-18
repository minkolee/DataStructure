package alog4e.chapter01.section02;

import alog4e.libs.Point2D;
import alog4e.libs.StdOut;

import java.util.Random;

public class EX010201 {

    public static void main(String[] args) {
        //生成N个随机点, 放入一个数组中
        int N = 2, i;
        Point2D[] points = new Point2D[N];

        Random random = new Random();
        for (i = 0; i < N; i++) {
            Point2D point = new Point2D(random.nextDouble(), random.nextDouble());
            points[i] = point;
        }

        StdOut.printf("Min distance is %f\n", getMinDistanceOfPoints(points));
    }

    //静态计算两点距离的函数
    public static double getDistance(Point2D a, Point2D b) {
        return Math.sqrt(Math.pow((a.x() - b.x()), 2) + Math.pow((a.y() - b.y()), 2));
    }

    //计算一组点中最小值的函数, 至少有两个点否则抛异常, 然后嵌套循环比较每一个点与数组中剩下的点的距离
    public static double getMinDistanceOfPoints(Point2D[] points) {
        if (points.length == 0) {
            throw new RuntimeException("There is no point.");
        }
        if (points.length == 1) {
            throw new RuntimeException("There is only one point");
        }

        double min = getDistance(points[0], points[1]);
        int i, j, length = points.length;
        for (i = 0; i < length; i++) {
            for (j = i + 1; j < length; j++) {
                double temp = getDistance(points[i], points[j]);
                if (temp < min) {
                    min = temp;
                }
            }
        }
        return min;
    }
}
