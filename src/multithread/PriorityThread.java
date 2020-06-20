package multithread;

public class PriorityThread {

    public static class High extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (PriorityThread.class) {
                    System.out.println(Thread.currentThread().getName() + " is working: " + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }


    public static void main(String[] args) {
        High thread1 = new High();
        High thread2 = new High();


        thread1.setPriority(10);
        thread2.setPriority(1);

        thread1.start();
        thread2.start();


    }

}
