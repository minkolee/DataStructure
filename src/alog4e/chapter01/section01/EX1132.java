package alog4e.chapter01.section01;

import alog4e.libs.StdDraw;
import alog4e.libs.StdOut;

import java.util.Arrays;
import java.util.Random;

public class EX1132 {

    static void draw(int N, double l, double r, double[] target) {
        if (N == 0) {
            throw new RuntimeException("N must be greater than 0.");
        }
        //生成从 l+ (r-l)/N, 到r, 以(r-l)/N 为间隔的对照数组, 然后用这个来将数放入对应的区间
        double[] standard = new double[N];
        double singledivider = (r - l) / N;
        int i;
        for (i = 0; i < N; i++) {
            standard[i] = l + singledivider * (i + 1);
        }
        //遍历target数组,将其与对照数组中每一个值比较, 如果小于等于对照组, 就将对照组加1然后跳出当前循环, 继续判断下一个target数组中的值
        int j;
        int[] result = new int[N];
        for (i = 0; i < target.length; i++) {
            if (target[i] > r || target[i] < l) {
                continue;
            }
            for (j = 0; j < N; j++) {
                if (target[i] <= standard[j]) {
                    result[j]++;
                    break;
                }
            }
        }
        //绘制图案, 用result2数组计算各个数量占总数量的比例
        int sum = 0;
        for (j = 0; j < N; j++) {
            sum += result[j];
        }
//        StdOut.println(Arrays.toString(standard));
//        StdOut.println(Arrays.toString(result));
//        StdOut.println(sum);
        double[] result2 = new double[N];
        for (j = 0; j < N; j++) {
            result2[j] = ((double)result[j]) / sum;
            StdDraw.filledRectangle(1.0*j/N, result2[j], 0.5/N, result2[j]);
        }
//        StdOut.println(Arrays.toString(result2));
    }

    public static void main(String[] args) {
        //组装N个在范围内的数组当参数
        Random random = new Random();
        int N = 100,i;
        double[] input = new double[N];
        for (i = 0; i < N; i++) {
            input[i] = random.nextDouble() * 2 + 3.0;
        }
//        StdOut.println(Arrays.toString(input));

        draw(10, 3, 5, input);
    }
}
