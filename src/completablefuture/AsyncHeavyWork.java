package completablefuture;

import java.util.Random;
        import java.util.concurrent.CompletableFuture;
        import java.util.concurrent.Future;

public class AsyncHeavyWork implements Delayable {

    private String rawMaterial;

    public AsyncHeavyWork(String rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public static Random random = new Random();

    public Future<Integer> getPriceAsync() throws Exception {

        CompletableFuture<Integer> future = new CompletableFuture<>();

        new Thread(() -> {
            int hash = rawMaterial.hashCode();
            delay();
            int result = random.nextInt(100) * hash / 10 + hash / (random.nextInt(50) + 1);
            future.complete(result);
        }).start();

        return future;

    }

}
