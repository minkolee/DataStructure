package datastructure.chapter10;

import java.util.Random;

public class TestLinkedQueue {


    public static void fillLinkedQueue(LinkedQueue<Integer> queue) {
        Random random = new Random();

        int count = random.nextInt(10) + 5;

        for (int i = 0; i < count; i++){

            queue.enqueue(random.nextInt(count * 5) + 1);

        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        fillLinkedQueue(linkedQueue);

        System.out.println(linkedQueue);

        while (linkedQueue.getFront() != null) {
            linkedQueue.show();

            System.out.println(linkedQueue.dequeue());
            System.out.println(linkedQueue);
        }

    }



}
