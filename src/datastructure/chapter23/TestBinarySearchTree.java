package datastructure.chapter23;

import java.util.Iterator;
import java.util.Random;

public class TestBinarySearchTree {

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            tree.add(i * 100 + 1);
        }

        Iterator<Integer> integerIterator = tree.iterator();

        System.out.print("| ");
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next());
            System.out.print(" | ");
        }
        System.out.println();
        System.out.println(tree.contains(1));
        System.out.println(tree.contains(209));
        System.out.println(tree.contains(301));
        System.out.println(tree.contains(1234));
        System.out.println("____________________________________________");

        System.out.println(tree.getEntry(12098));
        System.out.println(tree.getEntry(1));
        System.out.println(tree.getEntry(101));
        System.out.println(tree.getEntry(201));
        System.out.println(tree.getEntry(301));
        System.out.println(tree.getEntry(401));
        System.out.println(tree.getEntry(501));
        System.out.println(tree.getEntry(1501));
        System.out.println(tree.getEntry(12501));
        System.out.println(tree.getEntry(12123501));

    }

}
