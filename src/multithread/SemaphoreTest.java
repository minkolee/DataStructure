package multithread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {

    public static volatile int i = 0;

    public static Semaphore semaphore = new Semaphore(4);

    @Override
    public void run() {
        semaphore.acquireUninterruptibly();
        for (int j = 0; j < 100000; j++) {
            synchronized (semaphore) {
                i = i + 1;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 完成工作");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new SemaphoreTest());
            thread.start();
        }

        Thread.sleep(6000);

        System.out.println("i=" + i);
    }

}
