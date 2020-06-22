package multithread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantWithInterrupt {

    //设置两个公用的重入锁
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new MyThreadOnLock1();
        Thread thread2 = new MyThreadOnLock2();

        thread1.start();
        thread2.start();


        //主线程这里先等个2秒, 打断一次
        Thread.sleep(2000);
        thread2.interrupt();

        System.out.println("从两个线程的死锁中脱离");
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
                System.out.println("线程1获取了两个锁, 可以工作了.");
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
            try {
                lock2.lockInterruptibly();
                System.out.println("线程2 获取了 lock2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
                System.out.println("线程2 获取了 lock1");
                try {
                    System.out.println("线程2获取了两个锁, 可以工作了.");
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("等待锁的过程被打断, Thread2释放锁");
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
