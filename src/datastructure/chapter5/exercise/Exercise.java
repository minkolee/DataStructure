package datastructure.chapter5.exercise;

import alog4e.libs.In;
import datastructure.chapter5.LinkedListStack;
import datastructure.chapter5.Postfix;
import javafx.geometry.Pos;

public class Exercise {

    public static void main(String[] args) {

        System.out.println(Postfix.convertToPostfix("a*b/(c-d)"));
        System.out.println(Postfix.convertToPostfix("(a-b*c)/(d*e*f+g)"));
        System.out.println(Postfix.convertToPostfix("a/b*(c+(d-e))"));
        System.out.println(Postfix.evaluatePostfix("23+4*5-"));


        System.out.println(Exercise.count("0010"));

    }


    public static int count(String string) {
        //遇到0的时候, 将0放入栈中, 遇到1的时候, 如果栈顶是0, 就弹出


        LinkedListStack<Integer> value = new LinkedListStack<>();

        value.push(0);

        int length = string.length();

        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            int currentCount = value.pop();
            if (c == '0') {
                value.push(currentCount-1);
            } else {
                value.push(currentCount+1);
            }
        }

        return value.pop();
    }

}
