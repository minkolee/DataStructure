package multithread;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Long> {

    public long[] array;

    public int startIndex;

    public int endIndex;

    public ArraySumTask(long[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }


    @Override
    protected Long compute() {
        //如果要计算的数组范围小于等于100个, 就直接计算
        if (endIndex - startIndex <= 99) {

            System.out.println("索引差100, start");

            long sum = 0;

            for (int i = startIndex; i <= endIndex; i++) {
                sum = sum + array[i];
            }
            return sum;
            //如果要计算的数字范围大于100个, 就从中间拆分
        } else {

            int middleIndex = (startIndex + endIndex) / 2;
            System.out.println("启动startIndex=" + startIndex + " endIndex=" + (middleIndex - 1) + "的新任务");
            ArraySumTask task1 = new ArraySumTask(array, startIndex, middleIndex - 1);
            System.out.println("启动startIndex=" + middleIndex + " endIndex=" + endIndex + "的新任务");
            ArraySumTask task2 = new ArraySumTask(array, middleIndex, endIndex);

            invokeAll(task1, task2);
            Long result1 = task1.join();
            Long result2 = task2.join();

            return result1 + result2;
        }
    }
}