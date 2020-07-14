package multithread.designpatternsformultithread;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AssemblyLine {

    public static void main(String[] args) throws InterruptedException {
        First first = new First();
        Thread second = new Thread(new Second());
        Thread third = new Thread(new Third());

        first.start();
        second.start();
        third.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("请输入字符串, 输入qqq退出: ");
            String msg = scanner.nextLine();
            if (msg.equals("qqq")) {
                break;
            }
            System.out.println();
            first.offerRawMaterial(msg);
        }
        first.interrupt();
        second.interrupt();
        third.interrupt();

        first.join();
        second.join();
        third.join();

        System.out.println("完成工作");
    }

    public static class ProductMessage {
        String message;

        public ProductMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class First extends Thread {

        private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        public void offerRawMaterial(String material) throws InterruptedException {
            blockingQueue.put(material);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //使用阻塞方法take
                    String message = blockingQueue.take();
                    message = message + "第一道工序";
                    //使用阻塞方法put
                    Second.blockingQueue.put(message);
                } catch (InterruptedException e) {
                    System.out.println("被打断, 结束线程");
                    break;
                }
            }
        }
    }

    public static class Second implements Runnable {

        private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {

                try {
                    //使用阻塞方法take
                    String message = blockingQueue.take();
                    message = message + "第二道工序";
                    //使用阻塞方法put
                    Third.blockingQueue.put(message);
                } catch (InterruptedException e) {
                    System.out.println("被打断, 结束线程");
                    break;                }
            }
        }
    }

    public static class Third implements Runnable {

        private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    //使用阻塞方法take
                    String message = blockingQueue.take();
                    message = message + "第三道工序";
                    //使用阻塞方法put
                    System.out.println("有产品生产完成: " + message);
                } catch (InterruptedException e) {
                    System.out.println("被打断, 结束线程");
                    break;                }
            }
        }
    }
}
