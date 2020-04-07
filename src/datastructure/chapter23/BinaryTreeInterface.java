package datastructure.chapter23;

public interface BinaryTreeInterface<T> extends TreeInterface<T>, TreeIterator<T> {

    void setTree(T rootData);

    void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);

}
