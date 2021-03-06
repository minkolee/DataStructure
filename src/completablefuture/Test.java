package completablefuture;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newCachedThreadPool();

        List<Future<Integer>> works = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            works.add(pool.submit(new HeavyWork1(String.valueOf(i * 1000))));
        }

        try {
            for (Future<Integer> work : works) {
                int result = work.get(4, TimeUnit.SECONDS);
                System.out.println(work + "的结果是: " + result);
            }
        } catch (ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }


        pool.shutdown();
    }

}
