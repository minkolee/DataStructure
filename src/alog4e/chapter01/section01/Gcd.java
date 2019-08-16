package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

public class Gcd {
    static int count = 1;

    public static int gcd(int p, int q) {
        StdOut.println("The " + count + " recursion: " + "p is " + p + " q is " + q);
        count++;
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    public static void main(String[] args) {
        gcd(1111111, 1234567);
    }
}
