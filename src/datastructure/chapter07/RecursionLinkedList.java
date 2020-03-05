package datastructure.chapter07;

import datastructure.chapter5.LinkedListStack;

public class RecursionLinkedList {


    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.push("20");
        stack.push("312");
        stack.push("312");
        stack.push("312");
        stack.push("312");
        stack.push("312");
        stack.push("312");
        stack.push("312");
        stack.push("312098");
        stack.push("3148");

//        stack.showData();
//
//        stack.showDataFromOldest();

        System.out.println(stack.countNodes());
    }


}
