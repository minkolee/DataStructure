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


        for (int j = 0; j < 10; j++) {
            System.out.println("主线程做自己的事情");
            Thread.sleep(1000);
        }

        try {
            for (Future<Integer> work : works) {
                int result = work.get(2, TimeUnit.SECONDS);
                System.out.println(work + "的结果是: " + result);
            }
        } catch (ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
