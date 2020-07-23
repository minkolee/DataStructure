package io;


import java.nio.ByteBuffer;
import java.util.LinkedList;

public class ClientData {

    private LinkedList<ByteBuffer> outQueue;

    public ClientData() {
        outQueue = new LinkedList<>();
    }

    public LinkedList<ByteBuffer> getOutQueue() {
        return outQueue;
    }

    public void enqueue(ByteBuffer buffer) {
        outQueue.addFirst(buffer);
    }
}
