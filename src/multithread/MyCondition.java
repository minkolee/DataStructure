package multithread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class MyCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();


    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "干活啦...");
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            System.out.println("被打断了, 没事");
        }finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "干完活了");
    }

    public static void main(String[] args) throws InterruptedException {
        MyCondition myCondition1 = new MyCondition();
        MyCondition myCondition2 = new MyCondition();
        MyCondition myCondition3 = new MyCondition();

        Thread thread1 = new Thread(myCondition1);
        Thread thread2 = new Thread(myCondition2);
        Thread thread3 = new Thread(myCondition3);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("主线程去唤醒其他线程");

        Thread.sleep(1000);

        lock.lock();
        condition.signalAll();
        lock.unlock();

    }
}
