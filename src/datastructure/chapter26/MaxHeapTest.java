package datastructure.chapter26;

import java.util.Arrays;
import java.util.Random;

public class MaxHeapTest {


    public static void main(String[] args) {

        Integer[] aa = {12, 3, 5, 1, 12, 3, 5, 5, 12, 333, 3, 2, 425, 51, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        MaxHeap.sort(aa);

        System.out.println(Arrays.toString(aa));
    }
}
