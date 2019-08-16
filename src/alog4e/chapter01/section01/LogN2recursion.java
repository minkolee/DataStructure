package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

public class LogN2recursion {

    public static double ln(int N) {
        if (N == 1) {
            return 0;
        } else {
            return Math.log10(N) + ln(N - 1);
        }
    }

    public static void main(String[] args) {
        int N = 20;
        double result = ln(N);

        StdOut.println(result);
        int j;
        long i = 1;
        for (j = 1; j <= N; j++) {
            i *= j;
        }

        StdOut.println("verified " + Math.log10(i));

    }
}
