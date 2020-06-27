package multithread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest implements Runnable {
    static final CountDownLatch counter = new CountDownLatch(10);
    static final CountDownLatchTest demo = new CountDownLatchTest();


    //每个线程执行工作之后, 通知计数器减1
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            counter.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }

        counter.await();

        System.out.println("所有线程完成了工作");

        executorService.shutdown();
    }

}
