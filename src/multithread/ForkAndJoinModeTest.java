package multithread;


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ForkAndJoinModeTest {

    public static Random random = new Random();


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);

        //创建池子
        ForkJoinPool pool = new ForkJoinPool();
        //创建最初的任务
        ArraySumTask task = new ArraySumTask(array, 0, 1999);
        //提交任务
        Long result = pool.submit(task).get();

        System.out.println(result);

    }

}
