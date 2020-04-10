package datastructure.chapter23;

import java.util.Iterator;
import java.util.Random;

public class TestBinarySearchTree {

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            if (i % 2 == 0) {
                tree.add(i * 10 + 1);
            } else {
                tree.add(-i * 10 + 1);
            }


        }

        tree.add(11);

        Iterator<Integer> integerIterator = tree.iterator();

        System.out.print("| ");
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next());
            System.out.print(" | ");
        }
        System.out.println();
        System.out.println("____________________________________________");

        tree.iterRemove(41);
        tree.iterRemove(-89);
        tree.iterRemove(-69);
        tree.iterRemove(1);
        System.out.println(tree.iterRemove(81));
        System.out.println(tree.iterRemove(21));
        System.out.println(tree.iterRemove(61));
        System.out.println(tree.iterRemove(-49));
        System.out.println(tree.iterRemove(-29));
        System.out.println(tree.iterRemove(-9));
        System.out.println(tree.iterRemove(11));
        System.out.println(tree.iterRemove(-123));
        System.out.println(tree.iterRemove(-123));
        System.out.println(tree.iterRemove(-123));
        integerIterator = tree.iterator();

        System.out.print("| ");
        while (integerIterator.hasNext()) {
            System.out.print(integerIterator.next());
            System.out.print(" | ");
        }
    }

}
