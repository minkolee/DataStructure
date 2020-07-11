package multithread.FutureMode;

import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new MyTask());

        ExecutorService pool = Executors.newCachedThreadPool();

        //提交任务
        pool.submit(futureTask);

        pool.shutdown();

        futureTask.cancel(true);

        while (!futureTask.isDone()) {

            System.out.println(System.currentTimeMillis() + " 还没有完成任务.");

            Thread.sleep(1000);
        }

        if (futureTask.isCancelled()) {
            System.out.println("取消了");
        } else {
            System.out.println(futureTask.get());
        }



    }


}
