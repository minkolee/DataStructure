package multithread;

public class WrongLock {

    public static volatile int i = 0;

    public static class Worker extends Thread {
        @Override
        public void run() {
            int j = 0;
            while (j < 100000) {
                synchronized (WrongLock.class) {
                    i = i + 1;
                }
                j++;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();

        System.out.println("i=" + i);

    }
}
