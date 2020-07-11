package multithread.ThreadPool;

import java.util.concurrent.*;

public class CustomizeThreadPoolTest {

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>() {
        }, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                System.out.println("创建一个新线程");
                Thread t = new Thread(r);
                System.out.println("创建新线程: " + t.getName() + " 成功");
                return t;
            }
        }, new ThreadPoolExecutor.AbortPolicy()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行 "+ r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完毕 "+ r);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池运行结束.");
            }
        };

        for (int i = 0; i < 10; i++) {
            pool.execute(new MyThread());
        }
        pool.shutdown();
    }

}
