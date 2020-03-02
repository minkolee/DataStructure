package datastructure.chapter5;

import java.util.Arrays;
import java.util.Dictionary;

public class Postfix {

    public static String convertToPostfix(String expression) {
        if (expression == null) {
            return "";
        }

        LinkedListStack<Character> stack = new LinkedListStack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int length = expression.length();
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            switch (c) {

                case '^':
                case '(':
                    stack.push(c);
                    break;


                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && !big(c, stack.peek())) {
                        stringBuilder.append(stack.pop());
                    }
                    stack.push(c);
                    break;

                case ')':
                    while (stack.peek() != '(') {
                        stringBuilder.append(stack.pop());
                    }
                        stack.pop();
                    break;

                default:
                    stringBuilder.append(c);
                    break;
            }

//            System.out.println("当前的栈是: " + Arrays.toString(stack.toArray()));
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();

    }

    private static boolean big(Character c1, Character c2) {
        //第一种情况, 加减
        boolean condition1 = (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-');
        //第二种情况, 幂运算符始终高
        boolean condition2 = (c1 == '^');
        //第三种情况, 都是括号, 后边的一定比前边的大, 不是括号的也比括号大, 所以只要c2是括号, 就一定返回true
        boolean condition3 = (c2 == '(');
        //第四种情况, c1不是括号, c2是括号, 一定返回大

        return condition1 || condition2 || condition3;
    }

    public static int evaluatePostfix(String expression) {
        Stack<Integer> value = new LinkedListStack<>();

        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            switch (c){
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    int secondOp = value.pop();
                    int firstOp = value.pop();
                    value.push(evaluate(firstOp, secondOp, c));
                    break;
                default:
                    value.push(Integer.parseInt(String.valueOf(c)));
                    break;
            }
        }

        System.out.println("当前的栈是: " + Arrays.toString(value.toArray()));

        int result = value.pop();

        if (!value.isEmpty()) {
            throw new RuntimeException("表达式有误");
        }

        return result;
    }

    private static int evaluate(int firstOp, int secondOp, char operator) {
        switch (operator) {
            case '+':
                return firstOp + secondOp;
            case '-':
                return firstOp - secondOp;
            case '*':
                return firstOp * secondOp;
            case '/':
                return firstOp / secondOp;
            case '^':
                return sqrt(firstOp, secondOp);
            default:
                throw new RuntimeException("不支持运算符: " + operator);
        }
    }

    private static int sqrt(int firstOp, int secondOp) {
        int result = 1;
        for (int i = 0; i < secondOp; i++) {
            result *= firstOp;
        }
        return result;
    }

    public static int evaluateInfix(String expression) {
        Stack<Character> operatorStack = new LinkedListStack<>();
        Stack<Integer> valueStack = new LinkedListStack<>();

        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (operatorStack.isEmpty()) {
                        operatorStack.push(c);
                    } else if (big(c, operatorStack.peek())) {
                        operatorStack.push(c);
                    } else {
                        while (!operatorStack.isEmpty() && !big(c, operatorStack.peek())) {
                            int secondOp = valueStack.pop();
                            int firstOp = valueStack.pop();
                            valueStack.push(evaluate(firstOp, secondOp, operatorStack.pop()));
                        }
                        operatorStack.push(c);
                    }
                    break;
                case '^':
                case '(':
                    operatorStack.push(c);
                    break;
                case ')':
                    while (operatorStack.peek() != '(') {
                        int secondOp = valueStack.pop();
                        int firstOp = valueStack.pop();
                        valueStack.push(evaluate(firstOp, secondOp, operatorStack.pop()));
                    }
                    operatorStack.pop();
                    break;
                default:
                    valueStack.push(Integer.parseInt(String.valueOf(c)));
                    break;

            }
//            System.out.println("当前的操作符栈是: " + Arrays.toString(operatorStack.toArray()));
//            System.out.println("当前的值栈是: " + Arrays.toString(valueStack.toArray()));
//            System.out.println("-------------------------------------------------------------");

        }

        //结束之后不断弹栈求值
        while (!operatorStack.isEmpty()) {
            int secondOp = valueStack.pop();
            int firstOp = valueStack.pop();
            valueStack.push(evaluate(firstOp, secondOp, operatorStack.pop()));
        }

        int result = valueStack.pop();

        if (valueStack.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("表达式有误");
        }
    }



}
