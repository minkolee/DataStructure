package multithread;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTools {

    public static Map<String, Integer> unsafeMap = new ConcurrentHashMap<>();

    public static class Worker implements Runnable {
        @Override
        public void run() {
            synchronized (unsafeMap) {
                if (unsafeMap.containsKey("saner")) {
                    unsafeMap.put("saner", unsafeMap.get("saner") + 1);
                } else {
                    unsafeMap.put("saner", 0);
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            Thread thread = new Thread(new Worker());
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(unsafeMap.get("saner"));

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);

        queue.put("sf");
        queue.offer("sf");


    }

}
