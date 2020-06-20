package multithread;

public class ThreadGroupTest {

    //定义一个线程类
    public static class NormalThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("主线程尝试打断线程组");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        ThreadGroup tg = new ThreadGroup("FirstGroup");

        Thread thread1 = new Thread(tg, new NormalThread(), "T1");
        Thread thread2 = new Thread(tg, new NormalThread(), "T2");

        thread1.start();
        thread2.start();

        System.out.println(tg.activeCount());
        tg.list();

        Thread.sleep(4000);

        tg.interrupt();
    }

}
