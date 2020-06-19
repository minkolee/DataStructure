package multithread;

public class Join {

    public volatile static int i = 0;


    public static class Thread1 extends Thread {
        @Override
        public void run() {
            for (; i < 10000000; i++) {
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        thread1.join();
        System.out.println(i);

    }
}
