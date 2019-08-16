package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

public class Fibonacci {

    public static long F(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            long[] result = new long[N+1];
            result[0] = 0;
            result[1] = 1;
            int i;
            for (i = 2; i < N+1; i++) {
                result[i] = result[i - 1] + result[i - 2];
            }
            return result[N];
        }
    }

    public static void main(String[] args) {
        for (int N = 0; N < 100; N++) {
            StdOut.println(N + " " + F(N));
        }
    }
}
