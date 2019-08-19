package alog4e.chapter01.section02.exercise;

import alog4e.libs.StdIn;
import alog4e.libs.StdOut;

import java.util.Arrays;

/**
 * 好像用不到队列....
 */

public class EX010315 {
    public static void main(String[] args) {
        StdOut.println(args[0]);

        String input = StdIn.readLine();
        String[] array = input.split("\\s+");
        StdOut.println(Arrays.toString(array));
        StdOut.println(array[array.length - Integer.parseInt(args[0])]);
    }

}
