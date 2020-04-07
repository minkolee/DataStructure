package datastructure.chapter23;

public class BinaryTree<T> implements BinaryTreeInterface<T> {

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
}
