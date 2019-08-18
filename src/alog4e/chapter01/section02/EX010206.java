package alog4e.chapter01.section02;

import alog4e.libs.Interval1D;
import alog4e.libs.StdOut;

import java.util.Random;

public class EX010206 {

    public static boolean isCircularRottion(String s1, String s2) {
        //笨办法, 用循环去比较
        if (s1.length() != s2.length()) {
            throw new RuntimeException("Must has the same length.");
        }
        int i, length = s1.length();
        String s1_move = s1;
        for (i = 0; i < length; i++) {
            s1 = s1.substring(1) + s1.substring(0, 1);
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "ACTGACG";
        String s2 = "TGACGAC";
        StdOut.print(isCircularRottion(s1, s2));
    }
}
