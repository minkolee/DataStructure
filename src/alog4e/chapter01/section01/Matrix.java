package alog4e.chapter01.section01;

import alog4e.libs.StdOut;

import java.util.Arrays;

public class Matrix {

    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("Two vectors must have the same length.");
        }
        int i = 0, j = x.length;
        double result = 0.0;
        for (; i < j; i++) {
            result = result + (x[i] * y[i]);
        }
        return result;
    }

    /**
     *
     * @param a 一个矩阵
     * @param b 一个矩阵, a的列数必须和b的行数相等
     * @return 用转置矩阵的方法实现了列相乘, 否则循环会更复杂一些
     */
    public static double[][] mult(double[][] a, double[][] b) {

        if (a[0].length != b.length) {
            throw new RuntimeException("Matrix cannot be multiplied");
        }
        double[][] result = new double[a.length][b[0].length];
        //既然是i行乘以j列, 想到把b进行转置, 则j列就是转置后矩阵的j行了
        double[][] transposedB = transpose(b);

        int i, j;
        for (i = 0; i < a.length; i++) {
            for (j = 0; j < b[0].length; j++) {
                result[i][j] = dot(a[i], transposedB[j]);
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] a) {
        int row = a.length;
        int column = a[0].length;

        int i, j;
        double[][] result = new double[column][row];
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                result[j][i] =a[i][j];
            }
        }
        return result;
    }

    //矩阵乘以向量, 要求向量的长度要和矩阵的列数相同, 然后将向量转置, 与矩阵相乘
    public static double[] mult(double[][] a, double[] doubles) {
        //实际上就是a的每一行与向量的点积组成的向量
        int length = doubles.length;
        if (a[0].length != length) {
            throw new RuntimeException("This matrix's column does not equal to vector's length");
        }
        int i;
        double[] result = new double[length];

        for (i = 0; i < length; i++) {
            result[i] = dot(a[i], doubles);
        }
        return result;
    }

    //向量的长度是一行N列, 按照矩阵的要求, 后边矩阵的行数必须等于向量的列数
    //题目的意思应该是不考虑列向量和行向量的区别, 所以这里可以直接调用另外一个编写好的方法
    public static double[] mult(double[] y, double[][] a) {
        return mult(a, y);
    }

    public static void main(String[] args) {
        double[][] x = new double[][]{{5, 2, 4}, {3, 8, 2}, {6, 0, 4}};
        double[] y = new double[]{4, 5, 6};

        double[] result = mult(y, x);

        StdOut.println(Arrays.toString(result));
//        int i = result.length, j;
//        for (j = 0; j < i; j++) {
//            StdOut.println(Arrays.toString(result[j]));
//        }
    }

}
