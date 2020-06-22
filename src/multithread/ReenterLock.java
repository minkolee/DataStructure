package multithread;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        ReenterLock reenterLock1 = new ReenterLock();
        ReenterLock reenterLock2 = new ReenterLock();

        Thread thread1 = new Thread(reenterLock);
        Thread thread2 = new Thread(reenterLock1);
        Thread thread3 = new Thread(reenterLock2);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("执行完毕之后i=" + i);

    }
}
