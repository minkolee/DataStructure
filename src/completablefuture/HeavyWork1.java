package completablefuture;

import java.util.Random;
import java.util.concurrent.Callable;

public class HeavyWork1 implements Delayable, Callable<Integer> {

    private String rawMaterial;

    public HeavyWork1(String rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public static Random random = new Random();

    @Override
    public Integer call() throws Exception {
        int hash = rawMaterial.hashCode();
        //模拟阻塞1秒
        delay();
        return random.nextInt(100) * hash / 10 + hash / (random.nextInt(50) + 1);
    }

}
