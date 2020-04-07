package datastructure.chapter23;

class BinaryNode<T> {

    private T data;
    private BinaryNode<T> leftNode;
    private BinaryNode<T> rightNode;

    public BinaryNode() {
        this(null);
    }

    public BinaryNode(T data) {
        this.data = data;
    }

    public BinaryNode(T data, BinaryNode<T> leftNode, BinaryNode<T> rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public boolean isLeaf() {
        return leftNode == null && rightNode == null;
    }
}
