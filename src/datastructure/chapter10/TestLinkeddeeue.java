package datastructure.chapter10;

import java.util.Random;

public class TestLinkeddeeue {


    public static void fillLinkedQueue(DequeInterface<Integer> queue) {
        Random random = new Random();

        int count = random.nextInt(10) + 5;

        for (int i = 0; i < count; i++){

            queue.addToBack(random.nextInt(count * 5) + 1);

        }
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> linkedQueue = new LinkedDeque<>();

//        fillLinkedQueue(linkedQueue);
//
//        System.out.println(linkedQueue);
//
//        System.out.println("---------------------------");
//
//        while (linkedQueue.getBack() != null) {
//            System.out.println("从队列头弹出: " + linkedQueue.removeFront());
//            System.out.println(linkedQueue);
//        }
//
//        linkedQueue.addToFront(3);
//        System.out.println(linkedQueue);
//
//
//        linkedQueue.addToFront(99);
//        System.out.println(linkedQueue);
//
//        linkedQueue.addToFront(67);
//        System.out.println(linkedQueue);
//
//        linkedQueue.addToBack(21983);
//
//        linkedQueue.removeFront();
//        System.out.println(linkedQueue);
//
//        linkedQueue.removeBack();
//        System.out.println(linkedQueue);
//
//        linkedQueue.removeBack();
//        System.out.println(linkedQueue);


        System.out.println("像栈一样操作");

        linkedQueue.addToFront(10);
        linkedQueue.addToFront(20);
        linkedQueue.addToFront(30);

        System.out.println(linkedQueue);


        linkedQueue.removeFront();
        System.out.println(linkedQueue);
        linkedQueue.removeFront();
        System.out.println(linkedQueue);
        linkedQueue.removeFront();
        System.out.println(linkedQueue);

        System.out.println("像队列");

        linkedQueue.addToFront(10);
        linkedQueue.addToFront(20);
        linkedQueue.addToFront(30);


        linkedQueue.removeBack();
        System.out.println(linkedQueue);
        linkedQueue.removeBack();
        System.out.println(linkedQueue);
        linkedQueue.removeBack();
        System.out.println(linkedQueue);

    }



}
