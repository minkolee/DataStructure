package alog4e.chapter01.section02.exercise;

import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.StdIn;
import alog4e.libs.StdOut;

/**
 * 这题目的思路是, " [ ( { " 这三种类型的字符压入栈中, 遇到 "] ) }" 的时候, 弹栈并 进行匹配, 匹配不上就失败, 如果最后栈弹不光 ,也失败.
 */

public class EX010304 {
    //添加isFull, 只需要判断N是不是已经等于初始化的数组长度
    public static void main(String[] args) {
        boolean good = true;

        Stack<Character> stack = new Stack<>();
        StdOut.print("Please input some parentheses: ");
        String input = StdIn.readLine();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char temp = input.charAt(i), temp2;
            switch (temp) {
                case '{':
                case '(':
                case '[':
                    stack.push(temp);
                    break;
                case '}':
                    temp2 = stack.pop();
                    if (temp2 != '{') {
                        good = false;
                    }
                    break;
                case ']':
                    temp2 = stack.pop();
                    if (temp2 != '[') {
                        good = false;
                    }
                    break;
                case ')':
                    temp2 = stack.pop();
                    if (temp2 != '(') {
                        good = false;
                    }
                    break;
                default:
                    StdOut.println("There are characters out of parentheses!");
                    return;
            }
        }
        if (!stack.isEmpty()) {
            good = false;
        }
        StdOut.println(good);
    }
}
