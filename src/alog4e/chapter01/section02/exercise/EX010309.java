package alog4e.chapter01.section02.exercise;

import alog4e.chapter01.section02.stack.Stack;
import alog4e.libs.StdIn;
import alog4e.libs.StdOut;

/**
 * 也是两个栈, 一个保存操作数, 一个保存操作符
 * 遇到数字和操作符压入到对应的栈中, 遇到右括号就取出两个操作数, 和一个操作符, 然后压一个左括号进去
 * 这里用了一个小技巧, 就是将压栈的时候, 将字符串拼接成一起作为一个操作数压栈, 想法真的妙
 * 表达式这块比较搞, 还是看成熟算法参考一下
 */

public class EX010309 {
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
