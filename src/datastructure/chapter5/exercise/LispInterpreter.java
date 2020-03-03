package datastructure.chapter5.exercise;

import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Stack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class LispInterpreter {

    public static void main(String[] args) {
        boolean goOn = true;
        Scanner input = new Scanner(System.in);
        String expression;
        while (goOn) {
            System.out.println("请输入Lisp表达式, 例如(+(-6)(*2 3 4)).  输入quit退出: ");
            expression = input.nextLine();
            if (expression.equals("quit")) {
                goOn = false;
            } else {
                try {
                    System.out.println("你输入的表达式的结果是: " + evaluateLispExpression(expression));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static String evaluateLispExpression(String expression) {
        int length = expression.length();

        //存放字符串形式的值的栈
        Stack<String> valueStack = new LinkedListStack<>();
        //存放操作符形式的栈
        Stack<Character> operatorStack = new LinkedListStack<>();
        for (int i = 0; i < length; i++) {
            char currentChar = expression.charAt(i);

            switch (currentChar) {
                //左括号压值栈
                case '(':
                    valueStack.push(String.valueOf(currentChar));
                    break;
                //加减乘除, 压操作符栈
                case '+':
                case '-':
                case '*':
                case '/':
                    operatorStack.push(currentChar);
                    break;
                case ')':
                    //这里要解析表达式, 一个一个操作
                    //遇到右括号, 获取最近的操作符.
                    //将操作符和当前的值栈交给解析一对括号的函数来操作
                    char operator = operatorStack.pop();
                    parseSinglePairExpression(operator, valueStack);
                    break;
                case ' ':
                case '\n':
                case '\r':
                    break;
                //剩下的是字符, 需要往后判断, 直到一个空格或者右括号
                default:
                    //用一个j来判断空格或者右括号, 只要不是, 遇到字符就连续拼接
                    int j = i;
                    StringBuilder builder = new StringBuilder();
                    while (expression.charAt(j) != ')' && expression.charAt(j) != ' ' && expression.charAt(j)!='\r' && expression.charAt(j)!='\n') {
                        builder.append(expression.charAt(j));
                        j++;
                    }
                    //拼接完成之后的字符串应该就是操作数字符串, 将其压入栈中
                    valueStack.push(builder.toString());
                    //当前的j指向空格或者右括号, 将i设置为j-1, 这样下一次i就会扫描到j指向的位置
                    i = j - 1;
                    break;
            }
        }

        //正常结束的话, 最后的值栈中只有一个值, 操作符栈应该全部弹干净.

        String result = valueStack.pop().toString();

        if (valueStack.isEmpty() && operatorStack.isEmpty()) {
            return result;
        } else {
            throw new IllegalArgumentException("表达式有误");
        }
    }


    /**
     * 这个函数用来根据运算符从栈中取出操作数进行计算, 并将结果放入栈中
     * @param operator 单字符的操作符
     * @param valueStack 存放值的栈
     */
    private static void parseSinglePairExpression(char operator, Stack<String> valueStack) {
        Stack<BigDecimal> tempStack = new LinkedListStack<>();
        int count = 0;

        //只要栈顶不是左括号, 就反复弹出操作数到临时的栈中,并且计数.
        while (!valueStack.peek().equals("(")) {
            tempStack.push(new BigDecimal(valueStack.pop()));
            count++;
        }
        //然后弹出左括号
        valueStack.pop();

        //现在有了所有的按照顺序排列的操作数和操作符, 根据操作符来进行运算.
        //所有的运算正常结束的话, 都要压入计算所得的值.
        switch (operator) {
            case '+':
                //如果无操作数, 返回0
                if (count == 0) {
                    valueStack.push("0");
                //如果有操作数, 反复弹出结果, 相加即可
                } else {
                    BigDecimal sum = new BigDecimal("0");
                    while (!tempStack.isEmpty()) {
                        sum = sum.add(tempStack.pop());
                    }
                    //将结果压入值栈
                    valueStack.push(sum.toString());
                }
                break;

            case '-':
                //如果没有操作数就报异常
                if (count == 0) {
                    throw new IllegalArgumentException("减法必须至少有一个操作数");
                //如果只有一个操作数就取反
                } else if (count == 1) {
                    valueStack.push(tempStack.pop().negate().toString());
                //如果有两个以上操作数, 不断相减
                } else {
                    BigDecimal start = tempStack.pop();
                    while (!tempStack.isEmpty()) {
                        start = start.subtract(tempStack.pop());
                    }
                    valueStack.push(start.toString());
                }
                break;
            case '*':
                //乘法无操作数返回1
                if (count == 0) {
                    valueStack.push(new BigDecimal("1").toString());
                //至少有一个操作数, 连续相乘即可, 然后将结果字符串压入值栈
                } else {
                    BigDecimal start = tempStack.pop();
                    while (!tempStack.isEmpty()) {
                        start = start.multiply(tempStack.pop());
                    }
                    valueStack.push(start.toString());
                }
                break;

            case '/':
                if (count == 0) {
                    throw new IllegalArgumentException("除法必须至少有一个操作数");
                } else if (count == 1) {
                    BigDecimal result = new BigDecimal("1").divide(tempStack.pop(),2, RoundingMode.HALF_UP);
                    valueStack.push(result.toString());
                //超过两个操作数的情况下, 不断进行除法
                } else {
                    BigDecimal start = tempStack.pop();
                    while (!tempStack.isEmpty()) {
                        start = start.divide(tempStack.pop(),2, RoundingMode.HALF_UP);
                    }
                    valueStack.push(start.toString());
                }
                break;
            default:
                throw new IllegalArgumentException("操作符有误");
        }
    }


}
