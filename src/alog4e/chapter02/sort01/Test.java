package alog4e.chapter02.sort01;

import alog4e.chapter01.section04.Stopwatch;
import alog4e.libs.StdIn;
import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

import java.util.Arrays;

public class Test {

    public static double time(String alg, Comparable[] comparables) {
        long start = System.currentTimeMillis();
        if(alg.equals("Insertion")) Insertion.sort(comparables);
        else if(alg.equals("Selection")) Selection.sort(comparables);
        else{
            StdOut.println("未执行排序");
        }
        long end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public static double timeRandomInputs(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];

        //进行T次, 数组长度为N的排序, 将每次排序的时间累加到total上
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        StdOut.print("Input two INT, first is length, second is times: ");
        String a1 = StdIn.readLine();
        String[] a = a1.split("\\s+");
        StdOut.println(Arrays.toString(a));
        int N = Integer.parseInt(a[0]);
        int T = Integer.parseInt(a[1]);

        double Inserton_time = timeRandomInputs("Insertion", N, T);
        double Selection_time = timeRandomInputs("Selection", N, T);

        StdOut.printf("Insertion_time is %f, Selection_time is %f, rate is %f", Inserton_time, Selection_time, Inserton_time / Selection_time);
    }
}
