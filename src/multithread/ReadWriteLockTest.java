package multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    private static Lock lock = new ReentrantLock();
    //创建一个读写锁对象, 然后调用其中的方法获取读锁和写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readlock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    //模拟读操作, 线程睡眠一秒 然后返回value
    public int handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }  finally {
            lock.unlock();
        }
    }

    //模拟写操作 用index更新value
    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLockTest demo = new ReadWriteLockTest();

        //创建模拟读的线程, 使用读锁
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readlock);
//                    demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        //创建模拟写的线程, 使用写锁写入一个随机整数
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
//                    demo.handleWrite(lock,new Random().nextInt());
                    demo.handleWrite(writeLock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //创建20个读者和2个写者:
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread reader = new Thread(readRunnable);
            threads.add(reader);
            reader.start();
        }

        for (int j = 0; j < 2; j++) {
            Thread writer = new Thread(writeRunnable);
            threads.add(writer);
            writer.start();
        }

        //等待所有线程结束
        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("EndTime is :" + (System.currentTimeMillis() - start));

    }


}
