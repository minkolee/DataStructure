package datastructure.chapter23;

import alog4e.chapter01.section02.exercise.Node;
import datastructure.chapter5.LinkedListStack;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTreeInterface<T> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(T data) {
        root = new BinaryNode<>(data);
    }

    @Override
    public T getRootData() {
        if (isEmpty()) {
            throw new RuntimeException("树已经为空");
        } else {
            return root.getData();
        }
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


    @Override
    public boolean isEmpty() {
        return root == null;
    }


    @Override
    public void clear() {
        root = null;
    }

    //中序迭代器
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

    @Override
    public T add(T newEntry) {
        if (isEmpty()) {
            root = new BinaryNode<>(newEntry);
            return newEntry;

        } else return iterableAdd(newEntry);
    }

    private T add(T newEntry, BinaryNode<T> node) {
        if (newEntry.compareTo(node.getData()) == 0) {
            T result = node.getData();
            node.setData(newEntry);
            return result;
        } else if (newEntry.compareTo(node.getData()) < 0) {
            if (node.getLeftNode() != null) {
                return add(newEntry, node.getLeftNode());
            } else {
                node.setLeftNode(new BinaryNode<>(newEntry));
                return newEntry;
            }
        } else {
            if (node.getRightNode() != null) {
                return add(newEntry, node.getRightNode());
            } else {
                node.setRightNode(new BinaryNode<>(newEntry));
                return newEntry;
            }
        }
    }

    private T iterableAdd(T newEntry) {
        BinaryNode<T> currentNode = root;
        boolean found = false;
        T result = null;
        while (!found) {
            if (newEntry.compareTo(currentNode.getData()) == 0) {
                result = currentNode.getData();
                currentNode.setData(newEntry);
                found = true;
            } else if (newEntry.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeftNode() != null) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode.setLeftNode(new BinaryNode<>(newEntry));
                    found = true;
                }
            } else {
                if (currentNode.getRightNode() != null) {
                    currentNode = currentNode.getRightNode();
                } else {
                    currentNode.setRightNode(new BinaryNode<>(newEntry));
                    found = true;
                }
            }
        }

        return result;
    }


    @Override
    public boolean contains(T entry) {
        if (isEmpty()) {
            return false;
        } else return iterFind(entry);
    }

    private boolean iterFind(T entry) {
        BinaryNode<T> currentNode = root;
        boolean found = false;

        while (!found) {
            if (entry.compareTo(currentNode.getData()) == 0) {
                found = true;
            } else if (entry.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeftNode() == null) {
                    break;
                } else {
                    currentNode = currentNode.getLeftNode();
                }
            } else {
                if (currentNode.getRightNode() == null) {
                    break;
                } else {
                    currentNode = currentNode.getRightNode();
                }
            }
        }
        return found;
    }

    private boolean recursionFind(T entry, BinaryNode<T> node) {
        if (entry.compareTo(node.getData()) == 0) {
            return true;
        } else if (entry.compareTo(node.getData()) < 0) {
            if (node.getLeftNode() == null) {
                return false;
            } else {
                return recursionFind(entry, node.getLeftNode());
            }
        } else {
            if (node.getRightNode() == null) {
                return false;
            } else {
                return recursionFind(entry, node.getRightNode());
            }
        }
    }

    public class ReturnObject {
        private T entry;

        ReturnObject(T entry) {
            this.entry = entry;
        }

        public void setEntry(T entry) {
            this.entry = entry;
        }

        public T get() {
            return this.entry;
        }
    }

    private BinaryNode<T> getRootNode() {
        return this.root;
    }

    private void setRootNode(BinaryNode<T> newNode) {
        this.root = newNode;
    }

    public T remove(T entry) {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);
        return oldEntry.get();
    }

    /**
     * 在一棵树中删除指定的entry, 并且返回这棵树的根节点
     *
     * @param rootNode 递归删除的主方法
     * @param entry    要删除的项目
     * @param oldEntry 保存要删除项目的值的对象
     * @return 根节点
     */
    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry) {

        if (rootNode != null) {
            //先获取根节点的数据
            T rootData = rootNode.getData();

            //然后进行比较
            int comparison = entry.compareTo(rootData);

            //当前子树的根节点就是要删除的节点
            if (comparison == 0) {
                //在对象中保存要删除的节点的原来的数据
                oldEntry.setEntry(rootData);
                //从子树中删除根节点的方法
                rootNode = removeFromRoot(rootNode);
                //要查找的数据小于当前根节点, 要到左子树中进行操作.
            } else if (comparison < 0) {
                //获取左子树的根节点
                BinaryNode<T> leftChild = rootNode.getLeftNode();
                //对左子树进行相同的操作
                BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
                //将当前节点的左子树设置到新返回的子树的根节点
                rootNode.setLeftNode(subtreeRoot);

            } else {
                //对右侧也是同样的操作
                BinaryNode<T> rightChild = rootNode.getRightNode();
                rootNode.setRightNode(removeEntry(rightChild, entry, oldEntry));
            }
        }
        return rootNode;
    }

    /**
     * 这个方法是将三种操作都归于同一个起点, 即找到要删除的节点, 将其当做一个子树的根节点, 然后来进行操作
     *
     * @param rootNode 要删除的节点
     * @return 返回删除后新的根节点
     */
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
        //如果同时有两个子节点, 则不能简单的删除, 需要寻找前驱来替换
        if (rootNode.getRightNode() != null && rootNode.getLeftNode() != null) {

            //先获取当前节点的左子树节点
            BinaryNode<T> leftSubtreeRootNode = rootNode.getLeftNode();

            //在左子树中找到其前驱节点
            BinaryNode<T> frontNode = findLargestNode(leftSubtreeRootNode);

            //进行替换
            rootNode.setData(frontNode.getData());

            //更新当前节点的左侧子树为删除了前驱节点的子树
            rootNode.setLeftNode(removeLargest(leftSubtreeRootNode));


            //不满足上边条件, 则两个子节点至少有一个是null, 分别判断即可, 如果不是null, 只需要返回子节点, 这样上一层递归会接上当前节点的子节点, 也就相当于删除了当前节点

            //左边不为空就设置为左边
        } else if (rootNode.getLeftNode() != null) {
            rootNode = rootNode.getLeftNode();
            //左边为空就设置成右边, 不用管右边是不是为空, 为空也是正确的
        } else rootNode = rootNode.getRightNode();

        return rootNode;
    }

    private BinaryNode<T> findLargestNode(BinaryNode<T> rootNode) {
        BinaryNode<T> currentNode = rootNode;
        while (currentNode.getRightNode() != null) {
            currentNode = currentNode.getRightNode();
        }
        return currentNode;
    }

    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
        //如果没有右节点, 就返回左节点
        if (rootNode.getRightNode() == null) {
            return rootNode.getLeftNode();
            //如果有右节点, 将当前节点的右节点设置成删除最大之后返回的新子树的根节点
        } else {
            rootNode.setRightNode(removeLargest(rootNode.getRightNode()));
            return rootNode;
        }
    }

    @Override
    public T getEntry(T entry) {
        if (isEmpty()) {
            return null;
        } else {
            T result = null;
            BinaryNode<T> currentNode = root;
            boolean found = false;

            while (!found && currentNode != null) {
                if (entry.compareTo(currentNode.getData()) == 0) {
                    result = currentNode.getData();
                    found = true;
                } else if (entry.compareTo(currentNode.getData()) < 0) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }
            }
            return result;
        }
    }


    private class NodePair {
        private BinaryNode<T> targetNode;
        private BinaryNode<T> fatherNode;

        public BinaryNode<T> getTargetNode() {
            return targetNode;
        }

        public void setTargetNode(BinaryNode<T> targetNode) {
            this.targetNode = targetNode;
        }

        public BinaryNode<T> getFatherNode() {
            return fatherNode;
        }

        public void setFatherNode(BinaryNode<T> fatherNode) {
            this.fatherNode = fatherNode;
        }

        @Override
        public String toString() {
            return "NodePair{" +
                    "targetNode=" + targetNode +
                    ", fatherNode=" + fatherNode +
                    '}';
        }
    }

    public T iterRemove(T entry) {
        //声明返回值
        T result = null;

        //这里使用一个辅助方法, 寻找到entry所在的节点及其父节点, 创建一个nodePair并返回这个对象, 以供后续使用.
        NodePair nodePair = findNode(entry);

        //从NodePair中取出查找结果

        //targetNode存放要删除的节点
        BinaryNode<T> targetNode = nodePair.getTargetNode();

        //fatherNode是要删除的节点的父节点
        BinaryNode<T> fatherNode = nodePair.getFatherNode();

        //findNode方法中, 如果没有找到, 则targetNode会是null, 不进行任何操作

        //找到节点的情况下
        if (targetNode != null) {

            result = targetNode.getData();

            //如果这个节点有两个子节点
            if (targetNode.getLeftNode() != null && targetNode.getRightNode() != null) {

                //需要重新定位要删除的节点及其父节点, 这里再用一个辅助函数
                nodePair = findFrontNode(targetNode);

                //然后替换数据, 之后更新要删除的节点
                targetNode.setData(nodePair.getTargetNode().getData());
                targetNode = nodePair.getTargetNode();
                fatherNode = nodePair.getFatherNode();
            }

            //现在所有的情况都收敛成删除至多一个子节点的节点了.用一个辅助方法删除之
            removeTargetNode(fatherNode, targetNode);
        }

        return result;

    }


    public NodePair findNode(T entry) {
        BinaryNode<T> father = null;
        BinaryNode<T> target = root;
        boolean found = false;
        NodePair nodePair = new NodePair();

        while (target != null && !found) {
            if (entry.compareTo(target.getData()) == 0) {
                nodePair.setFatherNode(father);
                nodePair.setTargetNode(target);
                found = true;
            } else if (entry.compareTo(target.getData()) < 0) {
                father = target;
                target = target.getLeftNode();
            } else {
                father = target;
                target = target.getRightNode();
            }
        }

        if (!found) {
            nodePair.setTargetNode(null);
            nodePair.setFatherNode(null);
        }
        return nodePair;

    }


    private NodePair findFrontNode(BinaryNode<T> startNode) {
        //获取左子树的根节点
        BinaryNode<T> leftSubtreeNode = startNode.getLeftNode();

        //然后将当前节点当成父节点, leftSubtreeNode当成子节点, 开始进行搜索

        BinaryNode<T> father = startNode;
        BinaryNode<T> target = leftSubtreeNode;

        while (target.getRightNode() != null) {
            father = target;
            target = target.getRightNode();
        }
        //循环结束之后, target一定是一个没有右节点的节点, father是其父节点
        //注意, 也可能父节点就是startNode, 子节点就是startNode的左子节点, 不过同样满足我们的要求.

        //组装NodePair对象
        NodePair nodePair = new NodePair();
        nodePair.setFatherNode(father);
        nodePair.setTargetNode(target);
        return nodePair;
    }

    private void removeTargetNode(BinaryNode<T> fatherNode, BinaryNode<T> targetNode) {
        //从前边的逻辑中可以发现, 程序能进到这个方法里, targetNode一定不会为空, 而且至多就一个子节点
        //所以要看一下是不是根节点, 只需要重新设置根节点为其存在的那个子节点即可, 如果不是根节点, 那么father和target都存在, 删除之即可.

        //找到targetNode的至多一个的子节点
        BinaryNode<T> childNode;
        if (targetNode.getRightNode() != null) {
            childNode = targetNode.getRightNode();
        } else {
            childNode = targetNode.getLeftNode();
        }

        //如果删除的就是根节点, 就把子节点当成根节点
        if (targetNode == root) {
            setRootNode(childNode);

        //如果不是根节点, 判断targetNode是父节点的左儿子还是右女儿, 然后把childNode挂在对应位置即可
        } else if (fatherNode.getRightNode() == targetNode) {
            fatherNode.setRightNode(childNode);
        } else fatherNode.setLeftNode(childNode);
    }
}
