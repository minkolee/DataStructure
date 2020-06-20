package multithread;

public class DaemonThread {

    public static class MyDaemonThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("守护线程 " + Thread.currentThread().getName() + " 工作中...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyWorkerThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " is working: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        MyDaemonThread thread1 = new MyDaemonThread();
        MyWorkerThread thread2 = new MyWorkerThread();
        MyWorkerThread thread3 = new MyWorkerThread();

        //设置thread1为守护线程
//        thread1.setDaemon(true);

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
