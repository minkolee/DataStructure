package completablefuture;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Test2 {

    public static void main(String[] args) throws Exception {


        List<Future<Integer>> works = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            works.add(new AsyncHeavyWork(String.valueOf(i * 1000)).getPriceAsync());

        }

        try {
            for (Future<Integer> work : works) {
                int result = work.get(5, TimeUnit.SECONDS);
                System.out.println(work + "的结果是: " + result);
            }
        } catch (ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
