package alog4e.chapter01.section02.exercise;

import alog4e.chapter01.section02.quque.Queue;
import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.StdIn;
import alog4e.libs.StdOut;

/**
 * 这个可以简单尝试一下, 一开始假设 队列是 12345, 最早进入队列的是1
 * 执行第一个循环之后, 栈里是 54321
 * 执行第二个循环之后, 先弹出的5先进队列, 队列里变成了54321
 * 这实际上就是反转了队列顺序
 * 所以一个顺序集合可以使用栈来将顺序反转
 */

public class EX010306 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new Queue<>();

        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }
        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
    }
}
