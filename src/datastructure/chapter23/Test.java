package datastructure.chapter23;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        BinaryTree<String> tree2 = new BinaryTree<>("cony");
        BinaryTree<String> tree4 = new BinaryTree<>("owl");
        BinaryTree<String> tree6 = new BinaryTree<>("saner");
        BinaryTree<String> tree7 = new BinaryTree<>("wood");
        BinaryTree<String> tree5 = new BinaryTree<>("sitong", tree6, tree7);
        BinaryTree<String> tree3 = new BinaryTree<>("minko", tree4, tree5);
        BinaryTree<String> tree1 = new BinaryTree<>("jenny", tree3, tree2);

        System.out.println("-------------------------前序遍历---------------------------");
        tree1.preOrderTraversal(System.out::println);
        System.out.println("---------------------------中序遍历-------------------------");
        tree1.inOrderTraversal(System.out::println);
        System.out.println("---------------------------后序遍历-------------------------");
        tree1.postOrderTraversal(System.out::println);
        System.out.println("-------------------------迭代中序遍历-------------------------");
        tree1.inOrderIterationTraversal(System.out::println);

        System.out.println("-------------------------迭代层序遍历-------------------------");
        tree1.levelOrderIterationTraversal(System.out::println);

    }

}
