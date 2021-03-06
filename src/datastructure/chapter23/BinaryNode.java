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

    public BinaryNode<T> copy() {
        BinaryNode<T> newRoot = new BinaryNode<>(data);

        if (leftNode != null) {
            newRoot.setLeftNode(leftNode.copy());
        }

        if (rightNode != null) {
            newRoot.setRightNode(rightNode.copy());
        }
        return newRoot;
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode()));
        }
    }

    public int getNumberOfNodes() {
        return getNumberOfNodes(this);
    }

    private int getNumberOfNodes(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return getNumberOfNodes(node.getLeftNode()) + 1 + getNumberOfNodes(node.getRightNode());
        }
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                '}';
    }
}
