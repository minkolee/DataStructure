package datastructure.chapter23;

import java.util.Iterator;

public interface TreeIterator<T> {

    //前序迭代器
    Iterator<T> getPreorderIterator();

    //后序迭代器
    Iterator<T> getPostorderIterator();

    //中序迭代器
    Iterator<T> getInorderIterator();

    //层序遍历
    Iterator<T> getLevelOrderIterator();

}
