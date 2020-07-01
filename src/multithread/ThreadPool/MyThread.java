package multithread.ThreadPool;

//一个线程类
public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Start at " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
