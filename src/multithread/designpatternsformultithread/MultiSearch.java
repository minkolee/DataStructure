package multithread.designpatternsformultithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiSearch {

    private final List<Thread> threadList = new ArrayList<>();

    private final AtomicInteger result = new AtomicInteger(-1);

    private final int numberOfThreads = 6;

    private final int[] array;

    public MultiSearch(int[] array) {
        this.array = array;
    }

    public class SearchThread extends Thread {
        private final int startIndex;
        private final int endIndex;
        private int target;


        public SearchThread(int startIndex, int endIndex, int target) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.target = target;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始运行From" + startIndex + " to " + endIndex);
            boolean found = false;
            for (int i = startIndex; i < endIndex; i++) {
                //找到目标值
                if (array[i] == target) {
                    //尝试使用CAS设置值, 只尝试一次, 然后直接结束即可
                    //如果成功设置了, 说明是第一个找到的线程
                    //如果没有成功, 说明其他线程已经先找到, 所以直接break即可
                    System.out.println(Thread.currentThread().getName() + "找到了 " + target + " 索引是: " + i);
                    result.compareAndSet(-1, i);
                    found = true;
                    break;
                }
            }
            // 如果始终没有进过if, 运行到这里就是,一次都没找到, 直接结束了
            if (!found) {
                System.out.println(Thread.currentThread().getName() + "搜索结束, 没有找到");
            }

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
                System.out.println("索引范围是 " + i * step + "-->" + (step * (i + 1)));
                Thread searchThread = new SearchThread(i * step, step * (i + 1), target);
                threadList.add(searchThread);
                searchThread.start();
            }

            System.out.println("索引范围是 " + step * (numberOfThreads - 1) + "-->" + array.length);
            Thread searchThread = new SearchThread(step * (numberOfThreads - 1), array.length, target);
            threadList.add(searchThread);
            searchThread.start();

        }

        for (Thread t : threadList) {
            t.join();
        }

        return result.get();

    }

}


