package datastructure.chapter23;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        int data[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        BinarySearchTree<Integer> noBalanceTree = new BinarySearchTree<>();
        for (Integer s : data) {
            noBalanceTree.add(s);
        }

        for (Integer s : data) {
            System.out.println(noBalanceTree.findNode(s));
        }


        AVLBinarySearchTree<Integer> balanceTree = new AVLBinarySearchTree<>();
        for (Integer s : data) {
            balanceTree.add(s);
        }

        for (Integer s : data) {
            System.out.println(balanceTree.findNode(s));
        }

        balanceTree.remove(9);
        balanceTree.remove(10);
        balanceTree.remove(11);

        for (Integer s : data) {
            System.out.println(balanceTree.findNode(s));
        }
    }


}
