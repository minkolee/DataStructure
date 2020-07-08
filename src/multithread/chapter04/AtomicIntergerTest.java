package multithread.chapter04;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntergerTest {

    public static final AtomicIntegerFieldUpdater<UnsafeCounter> updater = AtomicIntegerFieldUpdater.newUpdater(UnsafeCounter.class, "counter");


    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        updater.incrementAndGet(unsafeCounter);
                    }
                }
            }.start();
        }

        Thread.sleep(2000);

        System.out.println("计数器是: " + unsafeCounter.getCounter() + " 应该是: 10000");

    }
}
