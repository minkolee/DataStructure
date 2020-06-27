package multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static class Worker implements Runnable {

        private String name;
        private final CyclicBarrier barrier;

        @Override
        public void run() {
            try {
                barrier.await();

                work();

                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public Worker(CyclicBarrier barrier, String name) {
            this.name = name;
            this.barrier = barrier;
        }

        void work() {
            System.out.println(name + "开始工作....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "工作结束...");
        }
    }

    public static class Commander implements Runnable {
        boolean flag;
        int N;

        public Commander(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }


        @Override
        public void run() {
            if (flag) {
                System.out.println("当前一批工人全部干完活了");

            } else {
                System.out.println("当前工人集合完毕, 出发干活");
                flag = true;
            }
        }

        public static void main(String[] args) {
            final int N = 40;
            Thread[] allSoldier = new Thread[N];
            boolean flag = false;
            CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Commander(flag, N));

            System.out.println("集合队伍");

            for (int i = 0; i < N; i++) {
                System.out.println("工人" + i + "报道");
                allSoldier[i] = new Thread(new Worker(cyclicBarrier, "士兵" + i));
                allSoldier[i].start();
            }

        }

    }


}
