package multithread;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true) {

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("发现中断标志位");
                        break;
                    } else {
                        System.out.println("未发现中断标志位");

                    }

                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        //Sleep中被打断, 会抛异常并清除中断标志位
                        System.out.println("Interrupted when sleep");
                        Thread.currentThread().interrupt();
                    }

                    Thread.yield();
                }
            }
        };

        t1.start();
        Thread.sleep(10000);
        System.out.println("主线程打断T1");
        t1.interrupt();

    }
}
