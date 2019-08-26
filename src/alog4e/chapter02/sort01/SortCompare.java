package alog4e.chapter02.sort01;

/******************************************************************************
 *  Compilation:  javac SortCompare.java
 *  Execution:    java SortCompare alg1 alg2 n trials
 *  Dependencies: StdOut.java Stopwatch.java
 *  
 *  Sort n random real numbers, trials times using the two
 *  algorithms specified on the command line.
 * 
 *  % java SortCompare Insertion Selection 1000 100 
 *  For 1000 random Doubles 
 *    Insertion is 1.7 times faster than Selection
 *
 *  Note: this program is designed to compare two sorting algorithms with
 *  roughly the same order of growth, e,g., insertion sort vs. selection
 *  sort or mergesort vs. quicksort. Otherwise, various system effects
 *  (such as just-in-time compiliation) may have a significant effect.
 *  One alternative is to execute with "java -Xint", which forces the JVM
 *  to use interpreted execution mode only.
 *
 ******************************************************************************/

import java.util.Arrays;

import alog4e.chapter01.section04.Stopwatch;
import alog4e.libs.StdOut;
import alog4e.libs.StdRandom;

public class SortCompare { 

    public static double time(String alg, Double[] a) {
        long start = System.currentTimeMillis();
        if (alg.equals("Insertion"))       Insertion.sort(a);
//        else if (alg.equals("InsertionX"))      InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection"))       Selection.sort(a); 
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("Shell"))           Shell.sort(a);
        else if (alg.equals("Merge"))           Merge.sort(a);
//        else if (alg.equals("MergeX"))          MergeX.sort(a);
        else if (alg.equals("MergeBU"))         MergeBU.sort(a);
        else if (alg.equals("Quick"))           Quick.sort(a);
        else if (alg.equals("Quick3way"))       Quick3way.sort(a);
//        else if (alg.equals("QuickX"))          QuickX.sort(a);
//        else if (alg.equals("Heap"))            Heap.sort(a);
        else if (alg.equals("System"))          Arrays.sort(a); 
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        long end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    } 

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials)  {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        } 
        return total; 
    } 

    // Use alg to sort trials random arrays of length n. 
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        } 
        return total; 
    } 

    public static void main(String[] args) { 
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Shell";
        String alg4 = "Merge";
        String alg5 = "MergeBU";
        String alg6 = "Quick";
        String alg7 = "Quick3way";

        int n = 1000;
        int trials = 1000;
        double time1, time2, time3, time4, time5, time6, time7;
        time1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
        time2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
        time3 = timeRandomInput(alg3, n, trials);   // Total for alg2.
        time4 = timeRandomInput(alg4, n, trials);   // Total for alg2.
        time5 = timeRandomInput(alg5, n, trials);   // Total for alg2.
        time6 = timeRandomInput(alg6, n, trials);   // Total for alg2.
        time7 = timeRandomInput(alg7, n, trials);   // Total for alg2.
        StdOut.printf("For %d times of length=%d array, result is:\n", trials, n);
        StdOut.printf("Insertion: %f, Selection: %f, Shell: %f\n", time1, time2, time3);
        StdOut.printf("Merge: %f, MergeBU: %f, Quick: %f\n", time4, time5, time6);
        StdOut.printf("Quick3way: %f\n", time7);

    }
} 
