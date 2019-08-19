package alog4e.chapter01.section02.exercise;

import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.StdOut;

/**
 * 这个静态方法实际上是复制一个栈, 由于接受栈可以访问目标栈的所有属性, 因此可以直接遍历然后组装链表
 */

public class EX010312 {
    public static void main(String[] args) {
        Stack<String> data = new Stack<>();
        Stack<String> opts = new Stack<>();
        String expression = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        StdOut.println(getCompleteExpression(expression));
    }

    public static String getCompleteExpression(String exp)
    {
        String[] params = exp.split(" ");
        Stack<String> oprStack = new Stack<String>();
        Stack<String> dataStack = new Stack<String>();
        for (int i = 0; i < params.length; i++) {
            if (isOperator(params[i])) {
                oprStack.push(params[i]);
            } else if (params[i].equals(")")) {
                String d1 = dataStack.pop();
                String d2 = dataStack.pop();
                String op = oprStack.pop();
                dataStack.push("( " + d2 + " " + op + " "+ d1 + " )");
            } else {
                dataStack.push(params[i]);
            }
        }
        return dataStack.pop();
    }

    private static boolean isOperator(String s)
    {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }
}
