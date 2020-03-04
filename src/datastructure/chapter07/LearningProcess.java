package datastructure.chapter07;

import java.lang.instrument.ClassDefinition;

public class LearningProcess {

    public static void skipLine(int n) {
        if (n != 0) {
            System.out.println();
            skipLine(n - 1);
        }
    }



    public static void drawCircle(int count, double radius){
        if(count > 0){
            System.out.println("画了一个半径: "+ radius +" 的圆");
            drawCircle(count - 1, radius * 4 / 3);
        }
    }


    public static void count(int n) {

        if (n >= 1) {
            count(n - 1);
            System.out.println(n);
        }
    }

    public static long fatoria(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (long) (n) * fatoria(n - 1);
        }
    }

    public static void displayArray(int[] array, int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            System.out.println(array[i]);
        }
    }

    public static void displayArrayRecursion(int[] array, int firstIndex, int lastIndex) {
        if (firstIndex <= lastIndex) {
            System.out.println(array[firstIndex]);
            displayArray(array, firstIndex+1,lastIndex);
        }
    }

    public static void displayArrayRecursionFromLast(int[] array, int firstIndex, int lastIndex) {
        if (firstIndex <= lastIndex) {
            displayArrayRecursionFromLast(array, firstIndex, lastIndex - 1);
            System.out.println(array[lastIndex]);
        }
    }


    public static void displayArrayRecursionByHalf(int[] array, int firstIndex, int lastIndex) {

        //停机条件依然是firstIndex > lastIndex,

        if (firstIndex < lastIndex) {
            //显示前半部分
            displayArrayRecursionByHalf(array, firstIndex, (firstIndex+lastIndex)/2);
            //显示后半部分
            displayArrayRecursionByHalf(array, (firstIndex + lastIndex) / 2 + 1, lastIndex);
        //直到数组仅一个元素的时候才显示字符
        } else if (firstIndex == lastIndex) {
            System.out.println(array[firstIndex]);
        }

    }

    public static void displayArrayRecursionThreePart(int[] array, int firstIndex, int lastIndex) {
        //首先想好想好停机条件, 如果firstIndex>lastIndex, 什么也不做
        //first=last的时候, 打印字符
        //first<last的时候说明数组可分, 因此可以继续处理三个部分

        if (firstIndex == lastIndex) {
            System.out.println(array[firstIndex]);
        } else if (firstIndex < lastIndex) {
            //处理不含中间索引的左半部分
            displayArrayRecursionThreePart(array, firstIndex, (firstIndex + lastIndex) / 2 - 1);
            //显示中间的部分
            System.out.println(array[(firstIndex+lastIndex)/2]);
            //处理右半部分
            displayArrayRecursionThreePart(array, (firstIndex + lastIndex) / 2 + 1, lastIndex);
        }

    }


    public static void main(String[] args) {
        int[] array = new int[]{3, 2, 3, 4, 5, 6, 3, 2, 1};

        displayArray(array, 2, 5);
        System.out.println("---------------------------");
        displayArrayRecursionThreePart(array, 2, 5);
        System.out.println("---------------------------");

    }
}
