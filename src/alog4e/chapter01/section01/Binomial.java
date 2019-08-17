package alog4e.chapter01.section01;

import alog4e.libs.StdOut;



public class Binomial {
    private static int count = 0;

    /**
     *
     * @param N
     * @param k
     * @param p
     * @return 用数组来做的话, 创建一个 N+1,k+1的二维数组. 然后用一个循环填充数组, 注意初始条件, 对于第一列和第一行比较特殊. 对于其他的i>=1 和 j>=1的情况可以直接填充
     */

    public static double binomial(int N, int k, double p) {
        count++;
        StdOut.printf("第%d次调用\n", count);
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) {
            return 0.0;
        }
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    public static void main(String[] args) {
        double result = binomial(5, 3, 30.0);
        StdOut.println(result);

        result = binomial_array(5, 3, 30.0);
        StdOut.println(result);

    }

    public static double binomial_array(int N, int k, double p) {
        double[][] result = new double[N + 1][k + 1];

        int i, j;
        for (i = 0; i < N + 1; i++) {
            for (j = 0; j < k + 1; j++) {
                if (i == 0 && j == 0) {
                    result[i][j] = 1.0;
                } else if (i == 0 && j != 0) {
                    result[i][j] = 0.0;
                } else if (i != 0 && j == 0) {
                    result[i][j] = (1.0 - p) * result[i - 1][j];
                } else {
                    result[i][j] = (1 - p) * result[i - 1][j] + p * result[i - 1][j - 1];
                }
            }
        }
        return result[N][k];
    }
}
