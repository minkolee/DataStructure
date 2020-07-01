package multithread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("准备一秒钟后调用阻塞方法");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().suspend();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());

        thread.start();
        System.out.println("主线程尝试在启动副线程后就将其unpark()");
        LockSupport.unpark(thread);

        thread.resume();

        System.out.println("全部线程执行完毕");

    }


}
