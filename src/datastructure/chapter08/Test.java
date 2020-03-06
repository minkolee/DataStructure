package datastructure.chapter08;

import alog4e.chapter02.sort01.Insertion;
import alog4e.chapter02.sort01.Selection;
import datastructure.ex03.CarA;
import datastructure.ex03.CarB;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        CarA carA1 = new CarA(20, "dfkj");
        CarA carA2 = new CarA(14, "fd");
        CarA carA3 = new CarA(34, "sad");
        CarA carA4 = new CarA(20, "asd");
        CarA carA5 = new CarA(32, "asd");
        CarA carA6 = new CarA(12, "asd");

        CarB carB1 = new CarB(29, "dfkbbvcj");
        CarB carB2 = new CarB(21, "bjkd");
        CarB carB3 = new CarB(33, "bjsakd");
        CarB carB4 = new CarB(22, "asd");


        CarA[] carAS = new CarA[]{carA1, carA2, carA3, carA4, carA5, carA6};
        CarA[] carAS2 = new CarA[]{carA1, carA2, carA3, carA4, carA5, carA6};
        CarA[] carAS3 = new CarA[]{carA1,carA2};
        CarB[] cars = new CarB[]{carB1, carB2,carB3,carB4};

        System.out.println(Arrays.toString(carAS));
        InsertionSort.insertionRecursionSort(carAS);
        System.out.println(Arrays.toString(carAS));

        System.out.println(Arrays.toString(carAS2));
        InsertionSort.insertionSort(carAS2);
        System.out.println(Arrays.toString(carAS2));
//
//        System.out.println(Arrays.toString(carAS3));
//        InsertionSort.insertionRecursionSort(carAS3);
//        System.out.println(Arrays.toString(carAS3));
    }
}
