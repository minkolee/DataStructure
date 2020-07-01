package multithread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

    private static ExecutorService newPool = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            newPool.submit(new MyThread());
        }
        newPool.shutdown();
    }



}
