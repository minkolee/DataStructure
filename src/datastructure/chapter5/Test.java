package datastructure.chapter5;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        LinkedListStack<String> stack = new LinkedListStack<>();

        stack.push("cony");
        stack.push("owl");
        stack.push("kiwi");


        System.out.println(Arrays.toString(stack.toArray()));
        System.out.println(stack.peek());
        System.out.println(Arrays.toString(stack.toArray()));
        stack.push("kiwi2");
        System.out.println(Arrays.toString(stack.toArray()));


        Test test = new Test();
        System.out.println(test.checkBalance("[a{b/(c-d)+e/(f+g)}-h]"));
        System.out.println("----------------------------------");
        System.out.println(test.checkBalance("{a[b+(c+2)/d]+e)+f}"));
        System.out.println("----------------------------------");
        System.out.println(test.checkBalance("[a{b+[c(d+e)-f]+g}"));

        System.out.println(test.change("a*b-d"));

        System.out.println(test.big('*', '-'));
    }

    public boolean checkBalance(String expression) {
        boolean result = false;
        if (expression == null) {
            return result;
        }

        int length = expression.length();
        LinkedListStack<Character> stack = new LinkedListStack<>();
        Character popedCharacter;
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case '}':
                    popedCharacter = stack.pop();
                    if (popedCharacter != '{') {
                        return result;
                    }
                    break;
                case ']':
                    popedCharacter = stack.pop();
                    if (popedCharacter != '[') {
                        return result;
                    }
                    break;
                case ')':
                    popedCharacter = stack.pop();
                    if (popedCharacter != '(') {
                        return result;
                    }
                    break;
                default:
            }

            System.out.println("当前的栈是: " + Arrays.toString(stack.toArray()));
        }
        if (stack.isEmpty()) {
            result = true;
        }

        return result;
    }

    public String change(String expression) {
        if (expression == null) {
            return "";
        }
        LinkedListStack<Character> stack = new LinkedListStack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int length = expression.length();
        for (int i = 0; i < length; i++) {
            char c = expression.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (big(c, stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && !big(c, stack.peek())) {
                                stringBuilder.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                    break;
                default:
                    stringBuilder.append(c);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();

    }

    private boolean big(Character c1, Character c2) {
        return (c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-');
    }

    private boolean equal(Character c1, Character c2) {
        return (c1 == '*' || c1 == '/') && (c2 == '*' || c2 == '/') || (c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-');
    }

}
