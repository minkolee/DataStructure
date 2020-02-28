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

    }


}
