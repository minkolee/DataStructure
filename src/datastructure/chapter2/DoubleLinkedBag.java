package datastructure.chapter2;

public class DoubleLinkedBag<T> implements BagInterface<T> {

    private class Node {
        private T data;
        private Node next;
        private Node previous;

        private Node(T dataPortion) {
            this(dataPortion, null, null);
        }

        private Node(T dataPortion, Node nextNode, Node previous) {
            this.previous = previous;
            this.data = dataPortion;
            this.next = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    private Node firstNode;
    private int numberOfEntries;

    public DoubleLinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }


    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        //新节点的next指向firstNode
        newNode.next = firstNode;
        //新节点的previous指向null
        newNode.previous = null;
        //将第一个节点指向newNode
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }

        Node currentNode = firstNode;
        firstNode = firstNode.next;

        if (firstNode != null) {
            firstNode.previous = null;
        }
        currentNode.next = null;
        currentNode.previous = null;
        numberOfEntries--;
        return currentNode.data;
    }

    @Override
    public boolean remove(T anEntry) {
        return false;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        return 0;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int i = 0;
        Node currentNode = firstNode;
        while (i < numberOfEntries && currentNode != null){
            result[i++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }
}
