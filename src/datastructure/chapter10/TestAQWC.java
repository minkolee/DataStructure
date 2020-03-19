package datastructure.chapter10;

public class TestAQWC {

    public static void main(String[] args) {

        ArrayQueueWithCounter<String> queue = new ArrayQueueWithCounter<>();

        queue.show();

        queue.enqueue("fdakj");
        queue.show();

        queue.enqueue("fdaaf");
        queue.show();

        queue.enqueue("bxc");
        queue.show();

        queue.enqueue("avjk");
        queue.show();


        queue.enqueue("av123jk");
        queue.show();


        queue.enqueue("avjafk");
        queue.show();


        queue.enqueue("155");
        queue.show();

        queue.enqueue("123");
        queue.show();

        queue.enqueue("123124");
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

        queue.dequeue();
        queue.show();

//
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        queue.show();
//
        queue.enqueue("11");
        queue.enqueue("12");
        queue.show();

        queue.enqueue("17");
        queue.show();

        queue.enqueue("22222");
        queue.show();

        queue.enqueue("22222");
        queue.show();

        queue.enqueue("22222");
        queue.show();

        for (int i = 0; i < 17; i++) {
            System.out.println(queue.dequeue());
            queue.show();
        }



//
//        for (int i = 0; i < 17; i++) {
//            System.out.println(queue.dequeue());
//            queue.show();
//
//        }
//
//        queue.enqueue("3xx");
//        queue.enqueue("3xx");
//        queue.enqueue("3xx");
//        queue.enqueue("3xx");
//
//        queue.show();
    }
}
