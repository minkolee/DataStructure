package datastructure.chapter5.exercise;

import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;

public class LispInterpreter {


    public static int evaluateLispExpression(String expression) {

        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            Stack<Character> operatorStack = new LinkedListStack<>();
            Stack<Integer> valueStack = new LinkedListStack<>();

            switch (c) {
                //遇到左括号, 是表达式的开始, 将其放入栈中
                case '(':
                    operatorStack.push(c);
                    break;

                //遇到右括号, 先获取一个操作符, 然后把栈传递给处理函数.
                case ')':

            }
        }


        return 0;
    }

    private int evaluteSingleExpression(char operator, Stack<Integer> stack) {



        return 0;
    }

}
