package datastructure.chapter23;

import datastructure.chapter10.ArrayQueue;
import datastructure.chapter10.LinkedQueue;
import datastructure.chapter5.LinkedListStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BinaryTree<T> implements BinaryTreeInterface<T>, TreeIterator<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T data) {
        root = new BinaryNode<>(data);
    }

    public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(data, leftTree, rightTree);
    }

    //辅助方法, 将两棵树中的根节点, 设置到当前树的根节点的左右分支上
    private void privateSetTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        root = new BinaryNode<>(data);

        if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftNode(leftTree.root);
        }

        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != leftTree) {
                root.setRightNode(rightTree.root);
                //如果左右子树是同一棵, 就复制一份挂载上去.
            } else {
                root.setRightNode(rightTree.root.copy());
            }
        }

        //全部挂载完毕之后, 将参数的两个树清空, 这样便无法在外部访问新树
        if ((leftTree != null) && leftTree != this) {
            leftTree.clear();
        }

        if ((rightTree != null) && rightTree != this) {
            rightTree.clear();

        }

    }

    //这个方法本质上和构造器一样, 新建一个节点. 原来的树会丢失
    @Override
    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    //内部调用privateSetTree, 需要强制转型, 这个转型是安全的
    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    }

    @Override
    public T getRootData() {
        if (isEmpty()) {
            throw new RuntimeException("树已经为空");
        } else {
            return root.getData();
        }
    }

    public void setRootData(T rootData) {
        root.setData(rootData);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    @Override
    public int getHeight() {
        if (root == null) {
            return 0;
        } else {
            return root.getHeight();
        }
    }

    @Override
    public int getNumberOfNodes() {
        if (root == null) {
            return 0;
        } else {
            return root.getNumberOfNodes();
        }
    }

    //前序遍历的公有方法
    public void preOrderTraversal(Consumer<T> consumer) {
        this.preOrderTraversal(root, consumer);
    }

    //前序遍历的私有方法
    private void preOrderTraversal(BinaryNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            consumer.accept(node.getData());
            preOrderTraversal(node.getLeftNode(), consumer);
            preOrderTraversal(node.getRightNode(), consumer);
        }

    }

    //中序遍历的公有方法
    public void inOrderTraversal(Consumer<T> consumer) {
        this.inOrderTraversal(root, consumer);
    }


    //中序遍历的私有方法
    private void inOrderTraversal(BinaryNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.getLeftNode(), consumer);
            consumer.accept(node.getData());
            inOrderTraversal(node.getRightNode(), consumer);
        }

    }

    //后序遍历的公有方法
    public void postOrderTraversal(Consumer<T> consumer) {
        this.postOrderTraversal(root, consumer);
    }


    //后序遍历的私有方法
    private void postOrderTraversal(BinaryNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            postOrderTraversal(node.getLeftNode(), consumer);
            postOrderTraversal(node.getRightNode(), consumer);
            consumer.accept(node.getData());
        }

    }

    //迭代中序遍历的公有方法
    public void inOrderIterationTraversal(Consumer<T> consumer) {
        this.inOrderIterationTraversal(root, consumer);
    }

    //迭代中序遍历的私有方法
    private void inOrderIterationTraversal(BinaryNode<T> node, Consumer<T> consumer) {

        ArrayQueue<BinaryNode<T>> stack = new ArrayQueue<>();

        BinaryNode<T> currentNode = root;

        while (!stack.isEmpty() || currentNode != null) {

            //一路压到最左边的节点
            while (currentNode != null) {
                stack.enqueue(currentNode);
                currentNode = currentNode.getLeftNode();
            }


            //然后弹栈, 弹一个出来看看有没有右结点
            if (!stack.isEmpty()) {
                BinaryNode<T> nextNode = stack.dequeue();
                //弹出之后应用函数
                consumer.accept(nextNode.getData());

                //将当前节点变换成右侧节点, 继续进行相同的操作.
                currentNode = nextNode.getRightNode();
            }

        }

    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {

        LinkedListStack<BinaryNode<T>> stack = new LinkedListStack<>();

        BinaryNode<T> currentNode = root;

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || currentNode != null;
        }

        @Override
        public T next() {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeftNode();
            }

            //然后弹栈, 弹一个出来看看有没有右结点
            if (!stack.isEmpty()) {
                BinaryNode<T> nextNode = stack.pop();

                //将当前节点变换成右侧节点, 继续进行相同的操作.
                currentNode = nextNode.getRightNode();
                return nextNode.getData();
            } else {
                throw new RuntimeException("出现意外");
            }
        }
    }

    //迭代前序遍历的公有方法
    public void preOrderIterationTraversal(Consumer<T> consumer) {
        this.preOrderIterationTraversal(root, consumer);
    }

    //迭代前序遍历的私有方法
    private void preOrderIterationTraversal(BinaryNode<T> node, Consumer<T> consumer) {

        LinkedListStack<BinaryNode<T>> stack = new LinkedListStack<>();

        BinaryNode<T> currentNode = root;

        while (!stack.isEmpty() || currentNode != null) {
            //当前节点不为空直接应用Consumer, 然后入栈
            if (currentNode != null) {
                consumer.accept(currentNode.getData());
                stack.push(currentNode);
                currentNode = currentNode.getLeftNode();
                //当前节点为空
            } else {
                BinaryNode<T> nextNode = stack.pop();
                currentNode = nextNode.getRightNode();
            }
        }
    }

    //迭代层序遍历的公有方法
    public void levelOrderIterationTraversal(Consumer<T> consumer) {
        this.levelOrderIterationTraversal(root, consumer);
    }

    //迭代层序遍历的私有方法
    private void levelOrderIterationTraversal(BinaryNode<T> node, Consumer<T> consumer) {

        LinkedQueue<BinaryNode<T>> queue = new LinkedQueue<>();

        BinaryNode<T> currentNode = root;

        if (currentNode != null) {
            queue.enqueue(root);
        }

        while (!queue.isEmpty()) {
            BinaryNode<T> nextNode = queue.dequeue();
            consumer.accept(nextNode.getData());
            if (nextNode.getLeftNode() != null) {
                queue.enqueue(nextNode.getLeftNode());
            }

            if (nextNode.getRightNode() != null) {
                queue.enqueue(nextNode.getRightNode());
            }
        }
    }


}

