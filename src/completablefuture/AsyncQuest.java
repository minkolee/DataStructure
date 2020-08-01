package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncQuest {

    private static Random random = new Random();

    //模拟耗时运算
    private static int cal(int param) {
        int sleepTime = param * 1000;
        try {
            Thread.sleep(sleepTime);
            throw new IllegalArgumentException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sleepTime;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> cal(2)).exceptionally(ex -> {
            System.out.println(ex.toString());
            return -1;
        }).thenAccept(System.out::println);

        future.get();

    }

}
