package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

public class ArrayTransform {


    public static void arraytransform(int[][] array, int row, int column) {
        int i, j;
        for (j = 0; j < column; j++) {
            for (i = 0; i < row; i++) {
                StdOut.printf("%8d", array[i][j]);
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        int[][] re = {{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 8, 9, 10}};
        arraytransform(re, 3, 4);
    }
}
