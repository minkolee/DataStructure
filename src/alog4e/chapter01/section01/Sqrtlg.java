package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

public class Sqrtlg {

    public static int lg(long N) {
        int i = 1;
        for (; ; i++) {
            if (i * i > N) {
                break;
            }
        }
        return i - 1;
    }

    public static void main(String[] args) {
        StdOut.println(lg(0));
        StdOut.println(exR1(6));
    }

    public static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }
}
