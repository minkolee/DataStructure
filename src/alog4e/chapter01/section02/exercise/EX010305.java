package alog4e.chapter01.section02.exercise;

import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.StdIn;
import alog4e.libs.StdOut;

//使用栈打印二进制表示, 说明栈可以方便的反转一个东西

public class EX010305 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int i = StdIn.readInt();

        while (i > 0) {
            stack.push(i % 2);
            i /= 2;
        }

        for (int d : stack) {
            StdOut.print(d);
        }
    }
}
