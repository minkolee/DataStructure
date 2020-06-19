package multithread;

public class WaitAndNotify {

    final static Object object = new Object();

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": Thread1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ": Thread1 wait for object");
                    object.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + ": Thread1 ends");
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": Thread2 start! To notifyall");


                object.notifyAll();
                System.out.println(System.currentTimeMillis() + ": Thread2 ends");

                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread2.start();

        thread1.start();

    }


}
