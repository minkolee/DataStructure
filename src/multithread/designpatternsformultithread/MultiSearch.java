package multithread.designpatternsformultithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiSearch {

    private final ExecutorService pool = Executors.newCachedThreadPool();

    private final AtomicInteger result = new AtomicInteger(-1);

    private final int numberOfThreads = 6;

    private final

    int[] array;

    public MultiSearch(int[] array) {
        this.array = array;
    }

    public class SearchThread extends Thread {
        private final int  startIndex;
        private final int endIndex;
        private int target;


        public SearchThread(int startIndex, int endIndex, int target) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.target = target;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                //找到目标值
                if (array[i] == target) {
                    //尝试使用CAS设置值, 只尝试一次, 然后直接结束即可
                    //如果成功设置了, 说明是第一个找到的线程
                    //如果没有成功, 说明其他线程已经先找到, 所以直接break即可
                    result.compareAndSet(-1, i);
                    break;
                }
            }
            // 如果始终没有进过if, 运行到这里就是,一次都没找到, 直接结束了
        }
    }

    //查找方法
    public int search(int target) throws InterruptedException {
        //根据线程的数量, 将索引进行区分
        if (array.length <= 100) {
            Thread searchThread = new SearchThread(0, array.length - 1, target);
            searchThread.start();
            searchThread.join();
            if (result.get() == -1) {
                System.out.println("没有找到");
            } else {
                System.out.println("找到了, 索引是: " + result.get());
            }
        } else {
            int step = array.length / numberOfThreads;
            for (int i = 0; i < numberOfThreads - 1; i++) {
                Thread searchThread = new SearchThread(i * step, step * (i + 1), target);
                pool.execute(searchThread);

            }
        }

        return result.get();

    }

}


