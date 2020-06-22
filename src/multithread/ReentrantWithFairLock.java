package multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantWithFairLock {

    //设置两个公用的重入锁
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new MyThreadOnLock1();
        Thread thread2 = new MyThreadOnLock2();

        thread1.setPriority(10);
        thread2.setPriority(1);
        thread1.start();
        thread2.start();


        //主线程这里先等个2秒, 打断一次
        thread1.join();
        thread2.join();

        System.out.println("两个线程都执行完毕");
    }

    //这个线程类先获取lock1, 再获取lock2, 然后进行工作
    public static class MyThreadOnLock1 extends Thread {
        @Override
        public void run() {
            lock1.lock();
            System.out.println("线程1 获取了 lock1");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
            System.out.println("线程1 获取了 lock2");
            try {
                System.out.println("线程1获取了两个锁, 正常工作.");
            } finally {
                lock2.unlock();
                lock1.unlock();

            }

        }
    }

    //这个线程类先获取lock2, 再获取lock1, 然后进行工作
    public static class MyThreadOnLock2 extends Thread {
        @Override
        public void run() {
            while (true) {
                lock2.lock();
                System.out.println("线程2 获取了 lock2");
                //尝试等待1秒的锁
                try {
                    //如果1秒内获得锁
                    if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println("线程2 获取了两个锁, 开始工作");
                        //正常结束
                        lock1.unlock();
                        lock2.unlock();
                        break;
                    } else {
                        System.out.println("线程2 获取锁失败, 释放全部锁, 进入下一次循环");
                        if (lock1.isHeldByCurrentThread()) {
                            lock1.unlock();
                        }
                        if (lock2.isHeldByCurrentThread()) {
                            lock2.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("线程2 等待过程被中断, 释放所有锁, 进入下一次循环");
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                }
            }

        }
    }

}
